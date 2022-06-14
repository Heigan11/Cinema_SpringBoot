package edu.school21.cinema.repositories;

import edu.school21.cinema.models.UserSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSessionRepository {

    void addUserSession(UserSession userSession);

    List<UserSession> findAllByUserId(Long id);
}
