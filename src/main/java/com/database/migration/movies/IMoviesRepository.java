package com.database.migration.movies;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IMoviesRepository extends JpaRepository<Movies, String> {
}
