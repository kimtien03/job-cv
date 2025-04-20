package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.DTO.UserCreateDTO;
import com.example.BEJobApplication.DTO.UserDTO;
import com.example.BEJobApplication.Entity.User;
import com.example.BEJobApplication.Mapper.UserMapper;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Responsitory.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NoFoundException("Không có người dùng nào trong hệ thống.");
        }
        return users.stream()
                .map(UserMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy người dùng với ID: " + id));
        return UserMapper.toUserDTO(user);
    }
//

    public Boolean deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy người dùng với ID: " + id);
        }
        userRepository.deleteById(id);
        return true;
    }

    public UserDTO createUser(UserCreateDTO userDTO) {
        validateUser(userDTO);
        User user = UserMapper.toUserEntity(userDTO);
//        user.setPassword("123456");
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user = userRepository.save(user);
        return UserMapper.toUserDTO(user);
    }

    private void validateUser(UserCreateDTO user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new NoFoundException("Username không được để trống");
        }
        String password = user.getPassword();
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d])[A-Za-z\\d\\W]{8,}$";

        if (password == null || !password.matches(regex)) {
            throw new NoFoundException("Password phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt");
        }
        if (user.getEmail() == null || !user.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            throw new NoFoundException("Email không hợp lệ");
        }
        if (user.getRole() == null
                || (!user.getRole().equalsIgnoreCase("ADMIN") && !user.getRole().equalsIgnoreCase("USER"))) {
            throw new NoFoundException("Role phải là 'ADMIN' hoặc 'USER'");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new NoFoundException("Email đã tồn tại");
        }
        String gender = user.getGender();
        System.out.println("gender: " + gender);
        if (gender == null || (!gender.equalsIgnoreCase("Nam") && !gender.equalsIgnoreCase("Nữ"))) {
            throw new NoFoundException("Giới tính phải là 'Nam' hoặc 'Nữ'");
        }
    }

    public UserDTO updateUser(Integer id, UserCreateDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy người dùng với ID: " + id));
        validateUser(userDTO);
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setBirth_day(userDTO.getBirthDate());
        existingUser.setGender(userDTO.getGender());
        existingUser.setRole(userDTO.getRole());
        existingUser = userRepository.save(existingUser);
        return UserMapper.toUserDTO(existingUser);

    }

}
