package com.example.BEJobApplication.Controller;

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
@RequestMapping("/api/User")
public class UserController {

    @Autowired
    private UserService userService;

    // Chỉ cho phép ADMIN truy cập
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/GetAllUser")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (NoFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Cho phép tất cả người dùng đã đăng nhập (bao gồm ADMIN và USER)
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/GetUserByID/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (NoFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Chỉ ADMIN có thể tạo người dùng mới
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/Create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(200).body(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage(), "status", 400));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Lỗi máy chủ", "message", e.getMessage()));
        }
    }

    // Chỉ ADMIN có thể xóa người dùng
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(Map.of("message", "Xóa người dùng thành công"));
    }

    // Cho phép ADMIN và chính người dùng đó cập nhật thông tin
    @PreAuthorize("hasRole('ADMIN') or @userSecurityService.isCurrentUser(#id)")
    @PutMapping("/Update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(id, user);
            return ResponseEntity.status(200).body(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage(), "status", 400));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Lỗi máy chủ", "message", e.getMessage()));
        }
    }
}
