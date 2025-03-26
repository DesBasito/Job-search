package kg.attractor.ht49.services.Components;

import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.models.UserModel;
import kg.attractor.ht49.repositories.UserModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private  UserModelRepository userModelRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userModelRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
        return new User(user.getEmail(),user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole().getRole())));
    }
}
