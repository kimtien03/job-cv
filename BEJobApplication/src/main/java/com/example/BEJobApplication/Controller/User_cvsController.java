package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.DTO.UserCvsDTO;
import com.example.BEJobApplication.DTO.UserDTO;
import com.example.BEJobApplication.Service.User_cvsService;
import com.example.BEJobApplication.Exception.NoFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user-cvs")
public class User_cvsController {

    @Autowired
    private User_cvsService userCvsService;

    // Lấy tất cả CV của user
    @GetMapping("/GetAllUserCVS")
    public ResponseEntity<List<UserCvsDTO>> getAllUserCvs() {
        try {
            List<UserCvsDTO> usersCvs = userCvsService.getAllUserCvs();
            return new ResponseEntity<>(usersCvs, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/CreateUsercv")
    public ResponseEntity<?> createUserCvs(@RequestBody UserCvsDTO userCvsDTO) {
        try {
            UserCvsDTO createdUserCvs = userCvsService.createUserCvs(userCvsDTO);
            return ResponseEntity.ok(createdUserCvs);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage(), "status", 400));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Lỗi máy chủ", "message", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserCvsById(@PathVariable Integer id) {
        try {
            UserCvsDTO userCvsDTO = userCvsService.getUserCvsById(id);
            return ResponseEntity.ok(userCvsDTO);
        } catch (NoFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserCvs(@PathVariable Integer id, @RequestBody UserCvsDTO userCvsDTO) {

        try {
            UserCvsDTO updatedUserCvs = userCvsService.updateUserCvs(id, userCvsDTO);
            return ResponseEntity.ok(updatedUserCvs);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage(), "status", 400));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Lỗi máy chủ", "message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserCvs(@PathVariable Integer id) {
        userCvsService.deleteUserCvs(id);
        return ResponseEntity.ok(Map.of("message", "Xóa người dùng thành công"));
    }


}
