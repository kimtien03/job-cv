package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.DTO.UserCreateDTO;
import com.example.BEJobApplication.DTO.UserDTO;
import com.example.BEJobApplication.Entity.User;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;


    // Chỉ cho phép ADMIN truy cập
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/GetAllUser")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserDTO> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (NoFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
        // Chỉ ADMIN có thể xóa người dùng
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(Map.of("message", "Xóa người dùng thành công"));
    }
    // Chỉ ADMIN có thể tạo người dùng mới
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/Create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO user) {
        try {
            UserDTO createdUser = userService.createUser(user);
            return ResponseEntity.status(200).body(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage(), "status", 400));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Lỗi máy chủ", "message", e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/Update/{id}")
    public ResponseEntity<?> UpdateUser(@PathVariable Integer id,@RequestBody UserDTO user) {
        try {
            UserDTO createdUser = userService.updateUser(id,user);
            return ResponseEntity.status(200).body(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage(), "status", 400));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Lỗi máy chủ", "message", e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/GetDetailUser/{id}")
    public ResponseEntity<?> GetDetailUser(@PathVariable Integer id) {
        try {
            UserDTO users = userService.getUserById(id);
            return ResponseEntity.ok(users);
        } catch (NoFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
