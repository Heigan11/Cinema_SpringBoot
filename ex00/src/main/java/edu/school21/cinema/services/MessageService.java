package edu.school21.cinema.services;

import edu.school21.cinema.models.Message;
import edu.school21.cinema.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public void saveMessage(Message message) {
        messageRepository.saveAndFlush(message);
    }

    public List<Message> getChatHistory(Long id) {
        List<Message> messageList = messageRepository.findFirst10MessageByMovieIdOrderByIdDesc(id);
        Collections.reverse(messageList);
        return messageList;
    }
}
