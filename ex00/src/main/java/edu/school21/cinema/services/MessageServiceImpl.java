package edu.school21.cinema.services;

import edu.school21.cinema.models.Message;
import edu.school21.cinema.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    @Transactional
    public void saveMessage(Message message) {
        this.messageRepository.saveMessage(message);
    }

    @Override
    @Transactional
    public void removeMessage(Long id) {
        this.messageRepository.removeMessage(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Message getMessageById(Long id) {
        return this.messageRepository.getMessageById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> listMessages() {
        return this.messageRepository.listMessages();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> getChatHistory(Long id) {
        return this.messageRepository.getChatHistory(id);
    }
}
