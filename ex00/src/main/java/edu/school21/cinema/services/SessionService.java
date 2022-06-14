package edu.school21.cinema.services;

import edu.school21.cinema.models.Session;

import java.util.List;

public interface SessionService {

    void addSession(Session session);

    void updateSession(Session session);

    void removeSession(Long id);

    Session getSessionById(Long id);

    List<Session> listSessions();

    List<Session> getSessionByFilm(String filmName);
}
