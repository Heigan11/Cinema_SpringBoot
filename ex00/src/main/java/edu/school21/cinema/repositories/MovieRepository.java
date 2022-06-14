package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository {

    void saveMovie(Movie movie);

    void removeMovie(Long id);

    Movie getMovieById(Long id);

    List<Movie> findAllMovies();
}
