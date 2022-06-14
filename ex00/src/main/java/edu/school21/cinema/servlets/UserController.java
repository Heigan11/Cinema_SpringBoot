package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserService;
import edu.school21.cinema.services.UserSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserSessionService userSessionService;


    @GetMapping("/signUp/{id}")
    public String getSignUpPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("film_id", id);
        return "signUp";
    }

    @PostMapping("/signUp/{film_id}")
    public String registerUser(@ModelAttribute("user") User user, @PathVariable("film_id") Long film_id) {
        String tempPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(tempPassword));
        user.setAvatarId(0L);
        userService.saveUser(user);

        return "redirect:/admin/panel/films";
    }

    @GetMapping("/signIn/{id}")
    public String getSignInPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "signIn";
    }

    @PostMapping("/signIn/{id}")
    public String loginUser(@PathVariable("id") Long id,
                            @ModelAttribute("name") String name,
                            @ModelAttribute("password") String password,
                            HttpServletRequest req) {
        User user = userService.authorizeUser(name, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            userSessionService.saveSession(user, req.getRemoteAddr());
            return "redirect:/films/" + id + "/chat";
        }
        return "redirect:/signIn/" + id;
    }
}
