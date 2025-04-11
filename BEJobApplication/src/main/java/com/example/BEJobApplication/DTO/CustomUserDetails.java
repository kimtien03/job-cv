package com.example.bejobapplication.DTO;


import com.example.bejobapplication.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public User getUserEntity() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Nếu bạn có role thì lấy từ user.getRoles()
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // đổi theo field thực tế
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // hoặc user.getUsername()
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
