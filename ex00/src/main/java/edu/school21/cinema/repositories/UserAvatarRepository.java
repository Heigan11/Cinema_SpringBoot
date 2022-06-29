package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAvatarRepository extends JpaRepository<UserAvatar, Long> {
    List<UserAvatar> findAllByUserId(Long id);
    UserAvatar findUserAvatarById(Long id);
}
