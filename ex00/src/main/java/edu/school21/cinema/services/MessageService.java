package edu.school21.cinema.services;

import edu.school21.cinema.models.Message;

import java.util.List;

public interface MessageService {

    void saveMessage(Message message);

    void removeMessage(Long id);

    Message getMessageById(Long id);

    List<Message> listMessages();

    List<Message> getChatHistory(Long id);
}
