package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findFirst10MessageByMovieIdOrderByIdDesc(Long id);
}
