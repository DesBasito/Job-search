package kg.attractor.ht49.services.impl;

import kg.attractor.ht49.exceptions.UserNotFoundException;
import kg.attractor.ht49.repositories.UserModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsServiceImpl implements UserDetailsService {

    private final UserModelRepository userModelRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userModelRepository.findByEmail(username)
                .orElseThrow(()->new UserNotFoundException("User by email: " + username + " not found"));
    }
}
