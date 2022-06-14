package edu.school21.cinema.services;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.repositories.HallRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;

    @Override
    @Transactional
    public void saveHall(Hall hall) {
        if (hall.getSeats() > 0 && hall.getSeats() <= 1000)
            this.hallRepository.saveHall(hall);
    }

    @Override
    @Transactional
    public void removeHall(int id) {
        this.hallRepository.removeHall(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Hall getHallById(int id) {
        return this.hallRepository.getHallById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Hall> listHalls() {
        return this.hallRepository.listHalls();
    }
}
