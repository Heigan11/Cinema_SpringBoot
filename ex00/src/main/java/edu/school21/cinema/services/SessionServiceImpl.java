package edu.school21.cinema.services;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.repositories.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Override
    @Transactional
    public void addSession(Session session) {
        this.sessionRepository.addSession(session);
    }

    @Override
    @Transactional
    public void updateSession(Session session) {
        this.sessionRepository.updateSession(session);
    }

    @Override
    @Transactional
    public void removeSession(Long id) {
        this.sessionRepository.removeSession(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Session getSessionById(Long id) {
        return this.sessionRepository.getSessionById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Session> listSessions() {
        return this.sessionRepository.listSessions();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Session> getSessionByFilm(String filmName) {
        return this.sessionRepository.getSessionByFilm(filmName);
    }
}
