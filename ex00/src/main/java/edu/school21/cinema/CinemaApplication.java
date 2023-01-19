package edu.school21.cinema;

//import edu.school21.cinema.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.mail.MessagingException;


@SpringBootApplication
@EnableJpaRepositories("edu.school21.cinema.repositories")
public class CinemaApplication {

//    @Autowired
//    private EmailSenderService senderService;

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);

    }
//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerMail() throws MessagingException {
//        senderService.sendSimpleEmail("heigan11@gmail.com",
//                "This is email body",
//                "This is email subject");
//
//    }
}
