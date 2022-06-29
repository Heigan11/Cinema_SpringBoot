package edu.school21.cinema.services;

import edu.school21.cinema.models.Movie;
import edu.school21.cinema.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;


    public void saveMovie(Movie movie) {
        movieRepository.saveAndFlush(movie);
    }

    public void removeMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findMovieById(id);
    }

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }
}
