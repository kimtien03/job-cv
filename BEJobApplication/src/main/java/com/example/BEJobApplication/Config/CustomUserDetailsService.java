package com.example.BEJobApplication.Config;

import com.example.BEJobApplication.Responsitory.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import com.example.BEJobApplication.Entity.User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserReponsitory userRepository;

    public CustomUserDetailsService(UserReponsitory userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        User user = userRepository
                .findByEmailOrUsername(input, input)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng"));
        String role = "ROLE_" + user.getRole();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(role))
        );

    }
}
