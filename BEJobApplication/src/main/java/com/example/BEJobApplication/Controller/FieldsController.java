package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.DTO.FieldsDTO;
import com.example.BEJobApplication.Service.FieldsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fields")
public class FieldsController {

    @Autowired
    private FieldsService fieldsService;

    // Tạo mới Field
    @PostMapping("GetAllField")
    public ResponseEntity<FieldsDTO> createField(@RequestBody FieldsDTO fieldsDTO) {
        FieldsDTO createdField = fieldsService.createField(fieldsDTO);
        return new ResponseEntity<>(createdField, HttpStatus.CREATED);
    }

    // Lấy thông tin Field theo ID
    @GetMapping("GetDetailField/{id}")
    public ResponseEntity<FieldsDTO> getFieldById(@PathVariable Integer id) {
        FieldsDTO field = fieldsService.getFieldById(id);
        return new ResponseEntity<>(field, HttpStatus.OK);
    }

    // Cập nhật thông tin Field
    @PutMapping("/UpdateField/{id}")
    public ResponseEntity<FieldsDTO> updateField(@PathVariable Integer id, @RequestBody FieldsDTO fieldsDTO) {
        FieldsDTO updatedField = fieldsService.updateField(id, fieldsDTO);
        return new ResponseEntity<>(updatedField, HttpStatus.OK);
    }

    // Xóa Field theo ID
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Integer id) {
        fieldsService.deleteField(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
