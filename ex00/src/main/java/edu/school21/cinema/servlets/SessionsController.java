package edu.school21.cinema.servlets;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.services.HallService;
import edu.school21.cinema.services.MovieService;
import edu.school21.cinema.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Controller
public class SessionsController {

    private final SessionService sessionService;
    private final MovieService movieService;
    private final HallService hallService;

    @GetMapping("/admin/panel/sessions")
    public String Sessions(Model model) {
        model.addAttribute("sessions", sessionService.listSessions());
        model.addAttribute("movies", movieService.findAllMovies());
        model.addAttribute("halls", hallService.listHalls());
        return "sessions";
    }

    @GetMapping("/admin/panel/sessions/delete/{id}")
    public String deleteSession(@PathVariable("id") Long id) {
        sessionService.removeSession(id);
        return "redirect:/admin/panel/sessions";
    }

    @PostMapping("/admin/panel/sessions/update/{id}")
    public String updateSession(@PathVariable("id") Long id,
                                @ModelAttribute("cost") int cost) {
        Session session = sessionService.getSessionById(id);
        session.setCost(cost);
        sessionService.updateSession(session);
        return "redirect:/admin/panel/sessions";
    }

    @PostMapping("/admin/panel/sessions")
    public String addSession(@ModelAttribute("date") String date,
                             @ModelAttribute("cost") int cost,
                             @ModelAttribute("movie") Long movie_id,
                             @ModelAttribute("hall") int hall_id) {

        LocalDateTime dateTime = LocalDateTime.parse(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDateTime = dateTime.format(formatter);

        sessionService.addSession(new Session(0L, formattedDateTime, cost,
                movieService.getMovieById(movie_id), hallService.getHallById(hall_id)));
        return "redirect:/admin/panel/sessions";
    }
}
