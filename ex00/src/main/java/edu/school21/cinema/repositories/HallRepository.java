package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface HallRepository extends JpaRepository<Hall, Long> {
    void removeHallById(Long id);
    Hall findHallById(Long id);
}
