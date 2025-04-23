package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.DTO.SectionsDTO;
import com.example.BEJobApplication.Service.SectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
public class SectionsController {

    private final SectionsService sectionsService;

    @Autowired
    public SectionsController(SectionsService sectionsService) {
        this.sectionsService = sectionsService;
    }

    // Lấy tất cả các section
    @GetMapping
    public ResponseEntity<List<SectionsDTO>> getAllSections() {
        List<SectionsDTO> sections = sectionsService.getAllSections();
        return new ResponseEntity<>(sections, HttpStatus.OK);
    }

    // Lấy thông tin Section theo ID
    @GetMapping("/{id}")
    public ResponseEntity<SectionsDTO> getSectionById(@PathVariable Integer id) {
        SectionsDTO section = sectionsService.getSectionById(id);
        return new ResponseEntity<>(section, HttpStatus.OK);
    }

    // Tạo mới Section
    @PostMapping
    public ResponseEntity<SectionsDTO> createSection(@RequestBody SectionsDTO sectionsDTO) {
        SectionsDTO createdSection = sectionsService.createSection(sectionsDTO);
        return new ResponseEntity<>(createdSection, HttpStatus.CREATED);
    }

    // Cập nhật Section theo ID
    @PutMapping("/{id}")
    public ResponseEntity<SectionsDTO> updateSection(@PathVariable Integer id, @RequestBody SectionsDTO sectionsDTO) {
        SectionsDTO updatedSection = sectionsService.updateSection(id, sectionsDTO);
        return new ResponseEntity<>(updatedSection, HttpStatus.OK);
    }

    // Xóa Section theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Integer id) {
        sectionsService.deleteSection(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
