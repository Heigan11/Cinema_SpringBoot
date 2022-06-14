package edu.school21.cinema.repositories;

import edu.school21.cinema.models.UserSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserSessionRepositoryImpl implements UserSessionRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addUserSession(UserSession userSession) {
        entityManager.merge(userSession);
    }

    @Override
    public List<UserSession> findAllByUserId(Long id) {
        return entityManager.createQuery("Select f from UserSession as f where f.user.id = :id order by f.id desc", UserSession.class)
                .setParameter("id", id)
                .setMaxResults(5)
                .getResultList();
    }
}
