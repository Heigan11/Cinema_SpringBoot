package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository {

    void saveMessage(Message message);

    void removeMessage(Long id);

    Message getMessageById(Long id);

    List<Message> listMessages();

    List<Message> getChatHistory(Long id);
}
