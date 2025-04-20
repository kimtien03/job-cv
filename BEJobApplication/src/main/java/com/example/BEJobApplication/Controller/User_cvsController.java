package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.Entity.User_cvs;
import com.example.BEJobApplication.Service.User_cvsService;
import com.example.BEJobApplication.Exception.NoFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-cvs")
public class User_cvsController {

    @Autowired
    private User_cvsService userCvsService;

    // Lấy tất cả CV của user
    @GetMapping
    public ResponseEntity<List<User_cvs>> getAllUserCvs() {
        try {
            List<User_cvs> usersCvs = userCvsService.getAllUser();
            return new ResponseEntity<>(usersCvs, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Lấy CV theo ID
    @GetMapping("/{id}")
    public ResponseEntity<User_cvs> getUserCvsById(@PathVariable("id") Integer id) {
        try {
            User_cvs userCvs = userCvsService.getUserCvsById(id);
            return new ResponseEntity<>(userCvs, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Thêm mới một CV
    @PostMapping
    public ResponseEntity<User_cvs> createUserCvs(@RequestBody User_cvs userCvs) {
        try {
            User_cvs createdUserCvs = userCvsService.createUserCvs(userCvs);
            return new ResponseEntity<>(createdUserCvs, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Cập nhật một CV theo ID
    @PutMapping("/{id}")
    public ResponseEntity<User_cvs> updateUserCvs(@PathVariable("id") Integer id, @RequestBody User_cvs userCvsDetails) {
        try {
            User_cvs updatedUserCvs = userCvsService.updateUserCvs(id, userCvsDetails);
            return new ResponseEntity<>(updatedUserCvs, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Xóa một CV theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserCvs(@PathVariable("id") Integer id) {
        try {
            userCvsService.deleteUserCvs(id);
            return new ResponseEntity<>("CV đã được xóa thành công.", HttpStatus.NO_CONTENT);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
