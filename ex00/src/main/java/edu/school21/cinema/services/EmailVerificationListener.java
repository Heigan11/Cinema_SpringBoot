package edu.school21.cinema.services;

//import edu.school21.cinema.models.HostDetails;
import edu.school21.cinema.models.User;
//import edu.school21.cinema.services.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Objects;

@Service
@PropertySource("classpath:application.properties")
//public class EmailVerificationListener implements ApplicationListener<UserRegistrationEvent> {
public class EmailVerificationListener implements ApplicationListener<UserRegistrationEvent> {

//	@Autowired
//	private HostDetails hostDetails;

//	@Autowired
//	private SimpleMailMessage simpleMailMessage;

//	@Autowired
//	private EmailVerificationService emailVerificationService;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void onApplicationEvent(UserRegistrationEvent event) {
		User user = event.getUser();

//		String text = String.format(Objects.requireNonNull(simpleMailMessage.getText()), hostDetails.getHost()  + ":" + hostDetails.getServerPort() +  "/confirm/" + encodedVerificationId);

//		String verificationId = emailVerificationService.generateVerification(user.getEmail());
//		String encodedVerificationId = new String(Base64.getEncoder().encode(verificationId.getBytes()));

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("heigan11@gmail.com");
		message.setTo(user.getEmail());
//		message.setText("Confirm the registration: http://localhost:8080/confirm/" + encodedVerificationId);
		message.setText("Confirm the registration: http://localhost:8080/confirm/" + user.getVerificationId());
		message.setSubject("Registration!");

		javaMailSender.send(message);
		System.out.println("Mail Send...");
	}
}
