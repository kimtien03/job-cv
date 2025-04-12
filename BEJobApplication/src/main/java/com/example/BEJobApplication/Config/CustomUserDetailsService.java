package com.example.BEJobApplication.Config;

import com.example.BEJobApplication.Responsitory.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import com.example.BEJobApplication.Entity.User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserReponsitory userRepository;

    public CustomUserDetailsService(UserReponsitory userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        // ✅ Sử dụng Optional và orElseThrow đúng cách
        User user = userRepository
                .findByEmailOrUsername(input, input)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng"));

        // ✅ Trả về User của Spring Security
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),// d?nh danh b?ng email
                user.getPassword(),
                Collections.emptyList()
        );

    }
}
