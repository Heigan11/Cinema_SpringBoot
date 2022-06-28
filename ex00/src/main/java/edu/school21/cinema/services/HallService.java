package edu.school21.cinema.services;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.repositories.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@AllArgsConstructor
public class HallService {

    @Autowired
    private HallRepository hallRepository;

    public void saveHall(Hall hall) {
        if (hall != null && hall.getId() != null) {
            Hall temp = hallRepository.findHallById(hall.getId());
            if (temp != null && hall.getSeats() > 0 && hall.getSeats() <= 1000) {
                hallRepository.saveAndFlush(hall);
            }
        } else {
            assert hall != null;
            if (hall.getSeats() > 0 && hall.getSeats() <= 1000)
                hallRepository.saveAndFlush(hall);
        }
    }

    public void removeHall(Long id) {
        hallRepository.removeHallById(id);
    }

    public Hall getHallById(Long id) {
        return hallRepository.findHallById(id);
    }


    public List<Hall> listHalls() {
        return hallRepository.findAll();
    }
}

//
//    @Override
//    @Transactional
//    public void saveHall(Hall hall) {
//        if (hall.getSeats() > 0 && hall.getSeats() <= 1000)
//            this.hallRepository.saveHall(hall);
//    }
//
//    @Override
//    @Transactional
//    public void removeHall(int id) {
//        this.hallRepository.removeHall(id);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Hall getHallById(int id) {
//        return this.hallRepository.getHallById(id);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<Hall> listHalls() {
//        return this.hallRepository.listHalls();
//    }