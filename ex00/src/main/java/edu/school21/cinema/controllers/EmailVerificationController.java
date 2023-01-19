package edu.school21.cinema.controllers;

//import edu.school21.cinema.models.ProfileStatus;
//import edu.school21.cinema.models.User;
//import edu.school21.cinema.services.EmailVerificationService;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;

@AllArgsConstructor
@Controller
@RequestMapping("/")
public class EmailVerificationController {
//	@Autowired
//	EmailVerificationService emailVerificationService;

	private final UserService userService;

	@RequestMapping("confirm/{uuid}")
	public String mailregistration(@PathVariable String uuid) {
		System.out.println("Hello! UUID is: " + uuid);
//		byte[] verificationId = Base64.getDecoder().decode(uuid);
//		String username = emailVerificationService.getUsernameByVerificationId(new String(verificationId));
//		if (username != null) {
//			User user = userService.findUserByEmail(username);
//			user.setProfileStatus(ProfileStatus.CONFIRMED);
//			userService.update(user);
//			return "redirect:/user";
//		}
		User user = userService.findUserByVerificationId(uuid);
		System.out.println(uuid);
		System.out.println(user.getVerificationId());
		if (user != null && uuid.equals(user.getVerificationId())){
			System.out.println("EMAIL are confirmed!");
			user.setConfirmed(true);
			userService.saveUser(user);
			return "redirect:/";
		}
		return "redirect:/login";
	}
}
