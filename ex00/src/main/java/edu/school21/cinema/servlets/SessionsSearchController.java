package edu.school21.cinema.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import edu.school21.cinema.models.Movie;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.models.SessionSearch;
import edu.school21.cinema.services.MovieService;
import edu.school21.cinema.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class SessionsSearchController {

    private final SessionService sessionService;
    private final MovieService movieService;

    @GetMapping("/sessions/search")
    @ResponseBody
    public String SessionsSearch(@RequestParam("filmName") String filmName) throws JsonProcessingException {
        List<Session> sessionList = sessionService.getSessionByFilm(filmName);
        List<SessionSearch> sessionSearchList = new ArrayList<>();
        for (Session session : sessionList) {
            sessionSearchList.add(new SessionSearch(session));
        }
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(sessionSearchList);

        return json;
    }

    @GetMapping("/sessions/search/image/{id}")
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

    @GetMapping("/sessions/{id}")
    public String MovieInfo(@PathVariable("id") Long id, Model model) {
        model.addAttribute("session", sessionService.getSessionById(id));
        return "sessionInfo";
    }

    @GetMapping("/sessions")
    public String Sessions() {
        return "sessionsSearch";
    }
}
