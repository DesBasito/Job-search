package kg.attractor.ht49.services;

import kg.attractor.ht49.dto.users.EditUserDto;
import kg.attractor.ht49.models.User;

public interface ProfileService {
    void editProfile(EditUserDto user);
}
