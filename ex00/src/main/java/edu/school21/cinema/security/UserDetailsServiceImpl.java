package edu.school21.cinema.security;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userDetailsServiceImpl")
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

//    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<User> users = userRepository.findAllByName(username);
//        if (users != null && users.size() == 1){
//            User user = users.get(0);
        User user = userService.getUserByEmail(username);
        if (user != null) {
            return SecurityUser.fromUser(user);
        } else {
            throw new UsernameNotFoundException("User doesn't exists");
        }
    }
}
