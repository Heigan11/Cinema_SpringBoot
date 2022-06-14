package edu.school21.cinema.repositories;

import edu.school21.cinema.models.UserAvatar;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAvatarRepository {

    UserAvatar save(UserAvatar userAvatar);

    List<UserAvatar> findAllByUserId(Long id);

    UserAvatar findById(Long id);
}
