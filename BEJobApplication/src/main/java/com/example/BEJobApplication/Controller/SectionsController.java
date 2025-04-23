package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.Entity.Sections;
import com.example.BEJobApplication.Service.SectionsService;
import com.example.BEJobApplication.Exception.NoFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
public class SectionsController {

    @Autowired
    private SectionsService sectionsService;

    // Lấy tất cả sections
    @GetMapping
    public ResponseEntity<List<Sections>> getAllSections() {
        try {
            List<Sections> sectionsList = sectionsService.getAllSections();
            return new ResponseEntity<>(sectionsList, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Lấy section theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Sections> getSectionById(@PathVariable("id") Integer id) {
        try {
            Sections section = sectionsService.getSectionById(id);
            return new ResponseEntity<>(section, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Tạo mới một section
    @PostMapping
    public ResponseEntity<Sections> createSection(@RequestBody Sections section) {
        try {
            Sections createdSection = sectionsService.createSection(section);
            return new ResponseEntity<>(createdSection, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Cập nhật section
    @PutMapping("/{id}")
    public ResponseEntity<Sections> updateSection(@PathVariable("id") Integer id,
                                                  @RequestBody Sections sectionDetails) {
        try {
            Sections updatedSection = sectionsService.updateSection(id, sectionDetails);
            return new ResponseEntity<>(updatedSection, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Xóa section theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSection(@PathVariable("id") Integer id) {
        try {
            sectionsService.deleteSection(id);
            return new ResponseEntity<>("Section đã được xóa thành công.", HttpStatus.NO_CONTENT);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
