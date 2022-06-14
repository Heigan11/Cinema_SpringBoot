package edu.school21.cinema.servlets;

import edu.school21.cinema.models.Movie;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.MessageService;
import edu.school21.cinema.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Controller
public class MoviesController {

    private final MovieService movieService;
    private final MessageService messageService;

    @GetMapping("/admin/panel/films")
    public String Movies(Model model) {
        model.addAttribute("movies", movieService.findAllMovies());
        return "films";
    }

    @GetMapping("/admin/panel/films/image/{id}")
    @ResponseBody
    public byte[] getContent(@PathVariable("id") Long id) {
        try {
            Movie movie = movieService.getMovieById(id);
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toRealPath().toString();
            if (movie.getPosterUrl() == null)
                return FileUtils.readFileToByteArray(new File(s + "/src/main/webapp/images/no-img.jpg"));
            return FileUtils.readFileToByteArray(new File(movie.getPosterUrl()));
        } catch (IOException e) {
            return null;
        }
    }

    @GetMapping("/admin/panel/films/delete/{id}")
    public String deleteMovie(@PathVariable("id") Long id) {
        movieService.removeMovie(id);
        return "redirect:/admin/panel/films";
    }

    @PostMapping("/admin/panel/films/addPoster/{id}")
    public String addPoster(@RequestParam("file") MultipartFile file,
                            @PathVariable("id") Long id) throws IOException {

        if (movieService.getMovieById(id) == null)
            return "redirect:/admin/panel/films";

        if (file.getContentType().equals("image/png") ||
                file.getContentType().equals("image/jpeg") ||
                file.getContentType().equals("image/webp")) {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toRealPath().toString();
            String dirPath = s + "/src/main/webapp/images/" + id + "/";

            File f = new File(dirPath);
            if (!f.exists())
                f.mkdir();

            for (File myFile : f.listFiles())
                if (myFile.isFile())
                    myFile.delete();

            Path path = Paths.get(dirPath + file.getOriginalFilename());
            file.transferTo(path);

            Movie movie = movieService.getMovieById(id);
            movie.setPosterUrl(path.toString());
            movieService.saveMovie(movie);
        }
        return "redirect:/admin/panel/films";
    }

    @PostMapping("/admin/panel/films")
    public String addMovie(@ModelAttribute("movie") Movie movie) {
        movieService.saveMovie(movie);
        return "redirect:/admin/panel/films";
    }

    @GetMapping("/entrance/{id}")
    public String entrance(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "entrance";
    }

    @GetMapping("/films/{id}/chat")
    public String goChat(@PathVariable("id") Long id, Model model, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getSession().getAttribute("user") == null) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return "entrance";
        }
        model.addAttribute("movie", movieService.getMovieById(id));
        model.addAttribute("user", (User) req.getSession().getAttribute("user"));
        model.addAttribute("history", messageService.getChatHistory(id));
        return "chat";
    }
}
