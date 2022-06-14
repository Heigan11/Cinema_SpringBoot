package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        if (user.getId() != null) {
            User temp = entityManager.find(User.class, user.getId());
            if (temp != null) {
                entityManager.merge(user);
            }
        } else if (getUserByName(user.getName()).isEmpty()) {
            entityManager.merge(user);
        }
    }

    @Override
    public void removeUser(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null)
            entityManager.remove(user);
    }

    @Override
    public List<User> getUserByName(String name) {
        return entityManager.createQuery("Select f from User f WHERE f.name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

}
