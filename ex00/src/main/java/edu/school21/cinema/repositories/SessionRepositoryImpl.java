package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class SessionRepositoryImpl implements SessionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addSession(Session session) {
        entityManager.merge(session);
    }

    @Override
    public void updateSession(Session session) {
        Session temp = entityManager.find(Session.class, session.getId());
        if (temp != null) {
            entityManager.merge(session);
        }
    }

    @Override
    public void removeSession(Long id) {
        Session session = entityManager.find(Session.class, id);
        if (session != null)
            entityManager.remove(session);
    }

    @Override
    public Session getSessionById(Long id) {
        return entityManager.find(Session.class, id);
    }

    @Override
    public List<Session> listSessions() {
        return entityManager.createQuery("Select f from Session as f order by f.id", Session.class).getResultList();
    }

    @Override
    public List<Session> getSessionByFilm(String filmName) {
        List<Session> sessionList = entityManager.createQuery("Select f from Session as f order by f.id", Session.class).getResultList();
        List<Session> foundedSessionList = new ArrayList<>();
        for (Session session : sessionList) {
            String title = session.getMovie().getTitle();
            if (title.toLowerCase().contains(filmName.toLowerCase()))
                foundedSessionList.add(session);
        }
        return foundedSessionList;
    }
}
