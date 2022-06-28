package edu.school21.cinema.services;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.repositories.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;


    public void addSession(Session session) {
        sessionRepository.saveAndFlush(session);
    }


    public void updateSession(Session session) {
        sessionRepository.saveAndFlush(session);
    }

    public void removeSession(Long id) {
        sessionRepository.deleteById(id);
    }

    public Session getSessionById(Long id) {
        return sessionRepository.findSessionById(id);
    }


    public List<Session> listSessions() {
        return sessionRepository.findAll();
    }

    public List<Session> getSessionByFilm(String filmName) {
        List<Session> sessionList = sessionRepository.findAll();
        List<Session> foundedSessionList = new ArrayList<>();
        for (Session session : sessionList) {
            String title = session.getMovie().getTitle();
            if (title.toLowerCase().contains(filmName.toLowerCase()))
                foundedSessionList.add(session);
        }
        return foundedSessionList;
    }
}

