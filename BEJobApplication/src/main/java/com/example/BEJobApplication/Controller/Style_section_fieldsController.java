package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.Entity.Style_section_fields;
import com.example.BEJobApplication.Service.Style_section_fieldsService;
import com.example.BEJobApplication.Exception.NoFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/style-section-fields")
public class Style_section_fieldsController {

    @Autowired
    private Style_section_fieldsService styleSectionFieldsService;

    // Lấy tất cả style_section_fields
    @GetMapping
    public ResponseEntity<List<Style_section_fields>> getAllStyleSectionFields() {
        try {
            List<Style_section_fields> list = styleSectionFieldsService.getAllStyleSectionFields();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Lấy style_section_field theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Style_section_fields> getStyleSectionFieldById(@PathVariable("id") Integer id) {
        try {
            Style_section_fields styleSectionField = styleSectionFieldsService.getById(id);
            return new ResponseEntity<>(styleSectionField, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Tạo mới style_section_field
    @PostMapping
    public ResponseEntity<Style_section_fields> createStyleSectionField(@RequestBody Style_section_fields data) {
        try {
            Style_section_fields createdData = styleSectionFieldsService.create(data);
            return new ResponseEntity<>(createdData, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Cập nhật style_section_field
    @PutMapping("/{id}")
    public ResponseEntity<Style_section_fields> updateStyleSectionField(@PathVariable("id") Integer id,
                                                                        @RequestBody Style_section_fields updatedData) {
        try {
            Style_section_fields updated = styleSectionFieldsService.update(id, updatedData);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Xóa style_section_field
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStyleSectionField(@PathVariable("id") Integer id) {
        try {
            styleSectionFieldsService.delete(id);
            return new ResponseEntity<>("style_section_field đã được xóa thành công.", HttpStatus.NO_CONTENT);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
