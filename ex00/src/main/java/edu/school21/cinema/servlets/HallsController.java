package edu.school21.cinema.servlets;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.services.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class HallsController {

    private final HallService hallService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin/panel/halls")
    public String halls(Model model) {
        model.addAttribute("halls", hallService.listHalls());
        return "halls";
    }

    @GetMapping("/admin/panel/halls/delete/{id}")
    public String deleteHall(@PathVariable("id") int id) {
        hallService.removeHall(id);
        return "redirect:/admin/panel/halls";
    }

    @PostMapping("/admin/panel/halls/update/{id}")
    public String updateHall(@ModelAttribute("hall") Hall hall) {
        System.out.println("====" + hall);
        if (hall != null)
            hallService.saveHall(hall);
        return "redirect:/admin/panel/halls";
    }

    @PostMapping("/admin/panel/halls")
    public String addhall(@ModelAttribute("hall") Hall hall) {
        if (hall != null)
            hallService.saveHall(hall);
        return "redirect:/admin/panel/halls";
    }
}
