package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Role;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserService;
import edu.school21.cinema.services.UserSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserSessionService userSessionService;

    @GetMapping("/signUp")
//    public String getSignUpPage(@ModelAttribute("user") User user, Authentication a) {
    public String getSignUpPage(Authentication a) {
        if (a != null && a.getName() != null) {
//            User chat_user = userService.getOneUserByName(a.getName());
            User chat_user = userService.getUserByEmail(a.getName());
            if (chat_user.getRole().equals(Role.ADMIN)){
                return "redirect:/admin/panel/halls";
            }
            if (chat_user.getRole().equals(Role.USER)){
                return "redirect:/profile";
            }
        }
        return "signUp";
    }

    @PostMapping("/signUp")
    public String registerUser(@Valid @ModelAttribute User user,
                               BindingResult errors,
                               Model model,
                               HttpServletRequest request,
                               HttpServletResponse response) {

        if(errors.hasErrors()) {
            errors.resolveMessageCodes("validation:");
//            System.out.println("ERRORS:");
//            System.out.println(errors.getAllErrors().get(0).getDefaultMessage());

            model.addAttribute("error", errors.getAllErrors().get(0).getDefaultMessage());
            return "/signUp";
        }

        String tempPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(tempPassword));
        user.setAvatarId(0L);
        user.setRole(Role.USER);
        userService.saveUser(user);

        return "redirect:/login";
    }

    @GetMapping("/signIn")
    public String getSignInPage(Authentication a, Model model) {
        if (a != null && a.getName() != null) {
//            User user = userService.getOneUserByName(a.getName());
            User user = userService.getUserByEmail(a.getName());
            if (user.getRole().equals(Role.ADMIN)){
                return "redirect:/admin/panel/halls";
            }
            if (user.getRole().equals(Role.USER)){
                return "redirect:/profile";
            }
        }
        return "login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @PostMapping("/signIn/{id}")
    public String loginUser(@PathVariable("id") Long id,
                            @ModelAttribute("name") String name,
                            @ModelAttribute("password") String password,
                            HttpServletRequest req) {
        System.out.println("hello PostMapping signIn");
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
