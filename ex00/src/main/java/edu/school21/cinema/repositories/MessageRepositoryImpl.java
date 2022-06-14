package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Message;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class MessageRepositoryImpl implements MessageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveMessage(Message message) {
        if (message.getId() != null) {
            Message temp = entityManager.find(Message.class, message.getId());
            if (temp != null) {
                entityManager.merge(message);
            }
        } else {
            entityManager.merge(message);
        }
    }

    @Override
    public void removeMessage(Long id) {
        Message message = entityManager.find(Message.class, id);
        if (message != null)
            entityManager.remove(message);
    }

    @Override
    public Message getMessageById(Long id) {
        return entityManager.find(Message.class, id);
    }

    @Override
    public List<Message> listMessages() {
        return entityManager.createQuery("Select f from Message as f order by f.id", Message.class).getResultList();
    }

    @Override
    public List<Message> getChatHistory(Long id) {
        List<Message> messageList = entityManager.createQuery("Select f from Message f WHERE f.movie.id = :movieId order by f.id DESC", Message.class)
                .setParameter("movieId", id)
                .setMaxResults(20)
                .getResultList();
        Collections.reverse(messageList);
        return messageList;
    }
}
