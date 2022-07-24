package com.database.migration.movies;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/migration")
public class MoviesController {

    MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @PostMapping(path = "/start")
    public void start(@RequestBody String url)
    {
        moviesService.start(url);
    }
}
