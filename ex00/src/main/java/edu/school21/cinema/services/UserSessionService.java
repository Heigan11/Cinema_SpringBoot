package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserSession;
import edu.school21.cinema.repositories.UserSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserSessionService {

    private final UserSessionRepository userSessionRepository;

    public void saveSession(User user, String ip) {
        UserSession session = new UserSession();
        session.setUser(user);
        session.setDate(LocalDate.now());
        session.setTime(LocalTime.now());
        session.setIp(ip);
        userSessionRepository.saveAndFlush(session);
    }

    public List<UserSession> getAllUserSession(User user) {
        return userSessionRepository.findFirst5UserSessionByUserIdOrderByIdDesc(user.getId());
    }
}
