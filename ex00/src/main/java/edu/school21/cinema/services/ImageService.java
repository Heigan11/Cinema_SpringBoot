package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserAvatar;
import edu.school21.cinema.repositories.UserAvatarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageService {

    private final UserAvatarRepository userAvatarRepository;

    public List<UserAvatar> getAllUserImages(User user) {
        return userAvatarRepository.findAllByUserId(user.getId());
    }

    public UserAvatar saveImage(UserAvatar userAvatar) {
        return userAvatarRepository.saveAndFlush(userAvatar);
    }

    public UserAvatar getImageByUserId(User user) {
        return userAvatarRepository.findUserAvatarById(user.getAvatarId());
    }
}
