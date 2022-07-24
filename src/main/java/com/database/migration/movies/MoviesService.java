package com.database.migration.movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class MoviesService implements IMoviesService {
    IMoviesRepository iMoviesRepository;

    public int totalNumber;
    public int migratedNumber;
    public int failNumber;
    public int lastIndex;

    public Hashtable<String, MIGRATION_STATUS> statusDict;

    public enum MIGRATION_STATUS {
        STARTED,
        RESUMED,
        PROGRESS,
        ENDED
    }

    public MoviesService(IMoviesRepository iMoviesRepository) {
        this.iMoviesRepository = iMoviesRepository;
        this.totalNumber = 0;
        this.failNumber = 0;
        this.lastIndex = 0;
        this.migratedNumber = 0;
        this.statusDict = new Hashtable<String, MIGRATION_STATUS>();
    }


    private void getMovieData(String id) throws JSONException {
        String url = "https://api.themoviedb.org/3/find/" + id  + "?api_key=a7f7650bc6053c146e113d011a993b07&language=en-US&external_source=imdb_id";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity request = new HttpEntity(headers);


        ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.GET, request, String.class);

        final JSONObject obj = new JSONObject(response.getBody());
        final JSONArray movie = obj.getJSONArray("movie_results");

        final JSONObject m = movie.getJSONObject(0);



        System.out.println(m.getString("title"));
        System.out.println(m.getString("overview"));
        System.out.println(m.getString("poster_path"));
        System.out.println(m.getString("media_type"));
        System.out.println(m.getString("popularity"));
        System.out.println(m.getString("release_date"));
        System.out.println(m.getBoolean("video"));
        System.out.println(m.getLong("vote_average"));
        System.out.println(m.getLong("vote_count"));

        Movies movie_data;
        try{
            statusDict.put(url, MIGRATION_STATUS.PROGRESS);

            movie_data = new Movies(id, m.getString("title"), m.getString("overview"), m.getString("poster_path"), m.getString("media_type"), m.getString("popularity"), m.getString("release_date"), m.getBoolean("video"), m.getLong("vote_average"), m.getLong("vote_count"));
            iMoviesRepository.saveAndFlush(movie_data);
            this.migratedNumber++;

            statusDict.put(url, MIGRATION_STATUS.ENDED);

        }catch(Exception e)
        {
            this.failNumber++;
        }

    }

    @Override
    public void start(String url) {

        try {
            // create auth credentials
            String authStr = "list_provider_service:is_password_a_password?";
            String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);

            // create request
            HttpEntity request = new HttpEntity(headers);

            // make a request
            ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.GET, request, String.class);

            // get JSON response

            JSONArray json = new JSONArray(response.getBody());

            this.totalNumber = json.length();

            for(int i=this.lastIndex;i<3;i++)
            {
                getMovieData(json.getString(i));
                this.lastIndex = i;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
