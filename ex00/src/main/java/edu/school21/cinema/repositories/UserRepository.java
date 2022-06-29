package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByName(String name);
    User findUserById(Long id);
}
