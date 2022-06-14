package edu.school21.cinema.services;

import edu.school21.cinema.models.Movie;

import java.util.List;

public interface MovieService {

    void saveMovie(Movie movie);

    void removeMovie(Long id);

    Movie getMovieById(Long id);

    List<Movie> findAllMovies();
}
