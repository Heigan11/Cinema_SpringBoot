package edu.school21.cinema.services;

import edu.school21.cinema.models.Hall;

import java.util.List;

public interface HallService {

    void saveHall(Hall hall);

    void removeHall(int id);

    Hall getHallById(int id);

    List<Hall> listHalls();
}
