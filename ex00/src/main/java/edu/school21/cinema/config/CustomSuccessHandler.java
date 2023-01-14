package edu.school21.cinema.config;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserService;
import edu.school21.cinema.services.UserSessionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserSessionService userSessionService;
    private final UserService userService;

    public void onAuthenticationSuccess(HttpServletRequest req,
                                        HttpServletResponse res,
                                        Authentication a) throws IOException, ServletException {
        if (a != null && a.getName() != null) {
            User user = userService.getOneUserByName(a.getName());
            if (user != null){
                userSessionService.saveSession(user, req.getRemoteAddr());
            }
        }
        this.setAlwaysUseDefaultTargetUrl(true);
        this.setDefaultTargetUrl("/index");
        super.onAuthenticationSuccess(req, res, a);
    }
}
