package com.database.migration.movies;
import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "movies")
public class Movies {
    @Id
    @Column(name="id")
    private String id;
    private String title;
    private String overview;
    private String poster_path;
    private String media_type;
    private String popularity;
    private Date release_date;
    private Boolean video;
    private Long vote_average;
    private Long vote_count;


    public Movies() {

    }

    public Movies(String id, String title, String overview, String poster_path, String media_type, String popularity, String release_date, Boolean video, Long vote_average, Long vote_count) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.media_type = media_type;
        this.popularity = popularity;
        Date rel_date = null;
        try {
            rel_date = new SimpleDateFormat("yyyy-mm-dd").parse(release_date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.release_date = rel_date;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Long getVote_average() {
        return vote_average;
    }

    public void setVote_average(Long vote_average) {
        this.vote_average = vote_average;
    }

    public Long getVote_count() {
        return vote_count;
    }

    public void setVote_count(Long vote_count) {
        this.vote_count = vote_count;
    }
}



