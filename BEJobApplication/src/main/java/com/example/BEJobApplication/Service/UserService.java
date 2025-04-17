package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.Entity.User;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Responsitory.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserReponsitory userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByEmailOrUsername(String email, String username) {
        return userRepository.findByEmailOrUsername(email, username);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NoFoundException("Không có người dùng nào trong hệ thống.");
        }
        return users;
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy người dùng với ID: " + id));
    }

    public Boolean deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy người dùng với ID: " + id);
        }
        userRepository.deleteById(id);
        return true;
    }

    public User createUser(User user) {
        validateUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private void validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new NoFoundException("Username không được để trống");
        }

//        if (user.getEmail() == null || !user.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
//            throw new NoFoundException("Email không hợp lệ");
//        }
        String password = user.getPassword();
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        if (password == null || !password.matches(regex)) {
            throw new NoFoundException("Password phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt");
        }
        String gender = user.getGender();
        System.out.println("gender: "+gender);
        if (gender == null || (!gender.equalsIgnoreCase("Nam") && !gender.equalsIgnoreCase("Nữ"))) {
            throw new NoFoundException("Giới tính phải là 'Nam' hoặc 'Nữ'");
        }
    }

    public User updateUser(Integer id, User updatedUser) {
        if (updatedUser.getId() != null && !updatedUser.getId().equals(id)) {
            throw new NoFoundException("ID không thể thay đổi");
        }

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy người dùng với ID: " + id));

        validateUser(updatedUser);

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        existingUser.setBirth_day(updatedUser.getBirth_day());
        existingUser.setGender(updatedUser.getGender());

        return userRepository.save(existingUser);
    }

}
