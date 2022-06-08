package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository {

    void saveHall(Hall hall);

    void removeHall(int id);

    Hall getHallById(int id);

    List<Hall> listHalls();
}
