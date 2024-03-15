package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.models.User;
import kg.attractor.ht49.services.ProfileService;
import kg.attractor.ht49.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserService service;

    @Override
    public void editProfile(User user) {
        service.editUser(user);
    }
}
