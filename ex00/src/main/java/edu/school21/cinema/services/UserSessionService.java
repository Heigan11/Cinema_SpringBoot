package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserSession;

import java.util.List;

public interface UserSessionService {

    void saveSession(User user, String ip);

    List<UserSession> getAllUserSession(User user);
}
