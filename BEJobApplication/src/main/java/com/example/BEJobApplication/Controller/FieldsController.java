package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.Entity.Fields;
import com.example.BEJobApplication.Service.FieldsService;
import com.example.BEJobApplication.Exception.NoFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/api/fields")
public class FieldsController {

    @Autowired
    private FieldsService fieldsService;

    // Lấy tất cả các trường
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Fields>> getAllFields() {
        try {
            List<Fields> fieldsList = fieldsService.getAllFields();
            return new ResponseEntity<>(fieldsList, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Lấy thông tin trường theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Fields> getFieldById(@PathVariable("id") Integer id) {
        try {
            Fields field = fieldsService.getFieldById(id);
            return new ResponseEntity<>(field, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Tạo mới trường
    @PostMapping
    public ResponseEntity<Fields> createField(@RequestBody Fields field) {
        try {
            Fields createdField = fieldsService.createField(field);
            return new ResponseEntity<>(createdField, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Cập nhật trường
    @PutMapping("/{id}")
    public ResponseEntity<Fields> updateField(@PathVariable("id") Integer id,
                                              @RequestBody Fields updatedField) {
        try {
            Fields updated = fieldsService.updateField(id, updatedField);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Xóa trường theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteField(@PathVariable("id") Integer id) {
        try {
            fieldsService.deleteIndustry(id);  // Phương thức trong Service cần sửa lại tên cho đúng
            return new ResponseEntity<>("Trường đã được xóa thành công.", HttpStatus.NO_CONTENT);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
