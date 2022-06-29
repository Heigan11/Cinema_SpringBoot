package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
    List<UserSession> findFirst5UserSessionByUserIdOrderByIdDesc(Long id);
}
