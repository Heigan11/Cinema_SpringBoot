package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class HallRepositoryImpl implements HallRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveHall(Hall hall) {
        if (hall.getId() != 0) {
            Hall temp = entityManager.find(Hall.class, hall.getId());
            if (temp != null) {
                entityManager.merge(hall);
            }
        } else
            entityManager.merge(hall);
    }

    @Override
    public void removeHall(int id) {
        Hall hall = entityManager.find(Hall.class, id);
        if (hall != null)
            entityManager.remove(hall);
    }

    @Override
    public Hall getHallById(int id) {
        return entityManager.find(Hall.class, id);
    }

    @Override
    public List<Hall> listHalls() {
        return entityManager.createQuery("Select f from Hall as f order by f.id", Hall.class).getResultList();
    }
}
