package org.example.testareonline.service.implementation;

import lombok.AllArgsConstructor;
import org.example.testareonline.entity.User;
import org.example.testareonline.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

        List<GrantedAuthority> authorities = user.getRol() != null
                ? List.of(new SimpleGrantedAuthority("ROLE_" + user.getRol().toUpperCase()))
                : Collections.emptyList();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getParola(),
                authorities
        );
    }
}
