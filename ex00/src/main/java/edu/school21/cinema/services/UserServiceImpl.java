package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void saveUser(User user) {
        this.userRepository.saveUser(user);
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        this.userRepository.removeUser(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUserByName(String name) {
        return this.userRepository.getUserByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public User authorizeUser(String name, String password) {
        if (name != null && password != null) {
            List<User> users = getUserByName(name);
            if (users != null && users.size() == 1)
                if (passwordEncoder.matches(password, users.get(0).getPassword())) {
                    return users.get(0);
                }
        }
        return null;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }
}
