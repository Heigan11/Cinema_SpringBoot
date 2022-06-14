package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserAvatar;
import edu.school21.cinema.services.ImageService;
import edu.school21.cinema.services.UserService;
import edu.school21.cinema.services.UserSessionService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class ProfileController {

    private final UserService userService;
    private final UserSessionService userSessionService;
    private final ImageService imageService;

    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if (req.getSession().getAttribute("user") == null) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return "index";
        }

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        model.addAttribute("usersessions", userSessionService.getAllUserSession(user));
        model.addAttribute("user", user);
        model.addAttribute("image", imageService.getImageByUserId(user));
        model.addAttribute("images", imageService.getAllUserImages(user));

        return "profile";
    }

    @GetMapping("/profile/image/{id}")
    @ResponseBody
    public byte[] getContent(@PathVariable("id") Long id) {
        try {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toRealPath().toString();
            User user = userService.findUserById(id);
            if (user.getAvatarId() == 0L) {
                return FileUtils.readFileToByteArray(new File(s + "/src/main/webapp/images/default_avatar.png"));
            }
            UserAvatar pic = imageService.getImageByUserId(user);
            if (pic.getFilepath() == null)
                return FileUtils.readFileToByteArray(new File(s + "/src/main/webapp/images/default_avatar.png"));
            return FileUtils.readFileToByteArray(new File(pic.getFilepath()));
        } catch (IOException e) {
            return null;
        }
    }

    @PostMapping("/uploadAvatar/{id}")
    public String addAvatar(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) throws IOException {
        if (file.getContentType().equals("image/png") ||
                file.getContentType().equals("image/jpeg") ||
                file.getContentType().equals("image/webp")) {
            String fileName = file.getOriginalFilename();
            String type = file.getContentType();
            long size = file.getSize();
            String uniqueName = UUID.randomUUID() + "_" + fileName;

            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toRealPath().toString();
            String dirPath = s + "/src/main/webapp/images/avatars/";

            File f = new File(dirPath);
            if (!f.exists())
                f.mkdir();

            Path path = Paths.get(dirPath + uniqueName);
            file.transferTo(path);

            User user = userService.findUserById(id);
            UserAvatar image = new UserAvatar(null,
                    user,
                    fileName,
                    uniqueName,
                    path.toString(),
                    size,
                    type);
            UserAvatar saved = imageService.saveImage(image);
            user.setAvatarId(saved.getId());
            userService.saveUser(user);
        }
        return "redirect:/profile";
    }

    @PostMapping("/setImage/{id}")
    public String setAvatar(@PathVariable("id") Long id, HttpServletRequest req) {
        User user = userService.findUserById(id);
        long imageId = Long.parseLong(req.getParameter("img"));
        user.setAvatarId(imageId);
        userService.saveUser(user);
        return "redirect:/profile";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        return "redirect:/";
    }
}
