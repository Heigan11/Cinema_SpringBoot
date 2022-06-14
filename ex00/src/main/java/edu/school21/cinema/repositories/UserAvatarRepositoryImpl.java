package edu.school21.cinema.repositories;

import edu.school21.cinema.models.UserAvatar;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserAvatarRepositoryImpl implements UserAvatarRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserAvatar save(UserAvatar userAvatar) {
        return entityManager.merge(userAvatar);
    }

    @Override
    public List<UserAvatar> findAllByUserId(Long id) {
        return entityManager.createQuery("Select f from UserAvatar as f where f.user.id = :id order by f.id desc", UserAvatar.class)
                .setParameter("id", id)
                .setMaxResults(5)
                .getResultList();
    }

    @Override
    public UserAvatar findById(Long id) {
        return entityManager.find(UserAvatar.class, id);
    }
}
