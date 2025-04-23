package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.DTO.StyleSectionFieldsDTO;
import com.example.BEJobApplication.Service.Style_section_fieldsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/style-section-fields")
public class Style_section_fieldsController {

    private final Style_section_fieldsService styleSectionFieldsService;

    @Autowired
    public Style_section_fieldsController(Style_section_fieldsService styleSectionFieldsService) {
        this.styleSectionFieldsService = styleSectionFieldsService;
    }

    // Tạo mới hoặc cập nhật một Style_section_field
    @PostMapping
    public ResponseEntity<StyleSectionFieldsDTO> saveStyleSectionField(@RequestBody StyleSectionFieldsDTO styleSectionFieldsDTO) {
        StyleSectionFieldsDTO savedStyleSectionField = styleSectionFieldsService.save(styleSectionFieldsDTO);
        return new ResponseEntity<>(savedStyleSectionField, HttpStatus.CREATED);
    }

    // Lấy thông tin một Style_section_field theo ID
    @GetMapping("/{id}")
    public ResponseEntity<StyleSectionFieldsDTO> getStyleSectionFieldById(@PathVariable Integer id) {
        StyleSectionFieldsDTO styleSectionField = styleSectionFieldsService.getById(id);
        return new ResponseEntity<>(styleSectionField, HttpStatus.OK);
    }

    // Lấy tất cả các Style_section_field
    @GetMapping
    public ResponseEntity<List<StyleSectionFieldsDTO>> getAllStyleSectionFields() {
        List<StyleSectionFieldsDTO> styleSectionFields = styleSectionFieldsService.getAll();
        return new ResponseEntity<>(styleSectionFields, HttpStatus.OK);
    }

    // Xóa một Style_section_field theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStyleSectionField(@PathVariable Integer id) {
        styleSectionFieldsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
