package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserAvatar;

import java.util.List;

public interface ImageService {

    List<UserAvatar> getAllUserImages(User user);

    UserAvatar saveImage(UserAvatar userAvatar);

    UserAvatar getImageByUserId(User user);
}
