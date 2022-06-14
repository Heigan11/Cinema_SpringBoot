package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    void saveUser(User user);

    void removeUser(Long id);

    List<User> getUserByName(String name);

    User findUserById(Long id);
}
