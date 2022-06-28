package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findSessionById(Long id);
}
