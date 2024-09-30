package com.rituraj.notification.service;

import com.rituraj.notification.entity.User;
import com.rituraj.notification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(username);

        UserDetails userDetails = null;
        if (user.isPresent()) {
            userDetails = org.springframework.security.core.userdetails.User.
                    builder().
                    username(user.get().getEmail()).
                    password(user.get().getPassword()).
                    roles(user.get().getRoles()).
                    build();
            return userDetails;
        }
        throw new UsernameNotFoundException(username);
    }
}
