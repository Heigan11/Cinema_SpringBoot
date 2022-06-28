package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, Long> {
    void deleteMovieById(Long id);
    Movie findMovieById(Long id);

}
