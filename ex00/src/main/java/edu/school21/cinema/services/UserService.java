package edu.school21.cinema.services;

import edu.school21.cinema.models.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void removeUser(Long id);

    List<User> getUserByName(String name);

    User authorizeUser(String name, String password);

    User findUserById(Long id);
}
