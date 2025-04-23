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

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserReponsitory userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
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
        User user= userRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy người dùng với ID: " + id));
        return UserMapper.toUserDTO(user) ;
    }
//
    public Boolean deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy người dùng với ID: " + id);
        }
        userRepository.deleteById(id);
        return true;
    }

    public UserDTO createUser(UserDTO userDTO) {
        validateUser(userDTO);
        User user = UserMapper.toUserEntity(userDTO);
        user.setPassword("123456");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return  UserMapper.toUserDTO(user);
    }

    private void validateUser(UserDTO user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new NoFoundException("Username không được để trống");
        }

        if (user.getEmail() == null || !user.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            throw new NoFoundException("Email không hợp lệ");
        }
        if (user.getRole() == null ||
                (!user.getRole().equalsIgnoreCase("ADMIN") && !user.getRole().equalsIgnoreCase("USER"))) {
            throw new NoFoundException("Role phải là 'ADMIN' hoặc 'USER'");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new NoFoundException("Email đã tồn tại");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new NoFoundException("Username đã tồn tại");
        }



    }
    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy người dùng với ID: " + id));

        validateUser(userDTO);
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setBirth_day(userDTO.getBirthday());
        existingUser.setGender(userDTO.getGender());
        existingUser.setRole(userDTO.getRole());
        existingUser = userRepository.save(existingUser);
        return UserMapper.toUserDTO(existingUser);
    }

}
