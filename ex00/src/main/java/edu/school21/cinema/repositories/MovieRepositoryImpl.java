package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Movie;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class MovieRepositoryImpl implements MovieRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveMovie(Movie movie) {
        if (movie != null && movie.getId() != null) {
            Movie temp = entityManager.find(Movie.class, movie.getId());
            if (temp != null) {
                entityManager.merge(movie);
            }
        } else
            entityManager.merge(movie);
    }

    @Override
    public void removeMovie(Long id) {
        Movie movie = entityManager.find(Movie.class, id);
        if (movie != null)
            entityManager.remove(movie);
    }

    @Override
    public Movie getMovieById(Long id) {
        return entityManager.find(Movie.class, id);
    }

    @Override
    public List<Movie> findAllMovies() {
        return entityManager.createQuery("Select f from Movie as f order by f.id", Movie.class).getResultList();
    }
}
