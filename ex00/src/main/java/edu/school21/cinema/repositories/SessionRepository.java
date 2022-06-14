package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository {

    void addSession(Session session);

    void updateSession(Session session);

    void removeSession(Long id);

    Session getSessionById(Long id);

    List<Session> listSessions();

    List<Session> getSessionByFilm(String filmName);
}
