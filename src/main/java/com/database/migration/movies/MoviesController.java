package com.database.migration.movies;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/migration")
public class MoviesController {

    MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @PostMapping(path = "/start")
    public List<String> start(@RequestBody String url)
    {


        List<String> strings = new ArrayList();
        moviesService.statusDict.put(url, MoviesService.MIGRATION_STATUS.STARTED);
        strings.add("url:" + url);

        strings.add("state: " + moviesService.statusDict.get(url));

        moviesService.start(url);

        strings.add("totalItemsNumber: " + moviesService.totalNumber);
        return strings;
    }

    @PostMapping(path = "/resume")
    public List<String> resume(@RequestBody String url)
    {
        moviesService.lastIndex = 0;
        moviesService.start(url);


        List<String> strings = new ArrayList();

        strings.add("url:" + url);
        strings.add("totalItemsNumber: " + moviesService.totalNumber);
        strings.add("migratedItemsNumber: " + moviesService.migratedNumber);
        strings.add("failedItemsNumber: " + moviesService.failNumber);
        strings.add("state: " + moviesService.statusDict.get(url));


        return strings;
    }

    @GetMapping(path = "/progress/{url}")
    public List<String> progress(@PathVariable(value = "url") String url)
    {
        List<String> strings = new ArrayList();

        strings.add("url:" + url);
        strings.add("totalItemsNumber: " + moviesService.totalNumber);
        strings.add("migratedItemsNumber: " + moviesService.migratedNumber);
        strings.add("failedItemsNumber: " + moviesService.failNumber);
        strings.add("state: " + moviesService.statusDict.get(url));


        return strings;
    }

    @GetMapping(path = "/failed")
    public String failed()
    {
        return "fail";
    }
}
