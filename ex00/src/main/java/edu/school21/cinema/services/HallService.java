package edu.school21.cinema.services;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.repositories.HallRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HallService {

    private final HallRepository hallRepository;

    public void saveHall(Hall hall) {
        if (hall.getSeats() > 0 && hall.getSeats() <= 1000) {
            hallRepository.saveAndFlush(hall);
        }
    }

    public void removeHall(Long id) {
        hallRepository.deleteById(id);
    }

    public Hall getHallById(Long id) {
        return hallRepository.findHallById(id);
    }


    public List<Hall> listHalls() {
        return hallRepository.findAll();
    }
}