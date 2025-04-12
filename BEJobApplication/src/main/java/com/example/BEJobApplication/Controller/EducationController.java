package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.Entity.Education;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Service.EducationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Education")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @GetMapping("/GetAllEducation")
    public ResponseEntity<?> getAllEducation() {
        try {
            List<Education> educations = educationService.getAllEducation();
            return ResponseEntity.ok(educations);
        } catch (NoFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/GetEducationByID/{id}")
    public ResponseEntity<?> getEducationById(@PathVariable Integer id) {
        try {
            Education education = educationService.getEducationById(id);
            return ResponseEntity.ok(education);
        } catch (NoFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/Create")
    public ResponseEntity<?> createEducation(@RequestBody Education education) {
        try {
            Education created = educationService.createEducation(education);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", e.getMessage(), "status", 400));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    Map.of("error", "Lỗi máy chủ", "message", e.getMessage()));
        }
    }

    @PutMapping("/Update/{id}")
    public ResponseEntity<?> updateEducation(@PathVariable Integer id, @RequestBody Education education) {
        try {
            Education updated = educationService.updateEducation(id, education);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", e.getMessage(), "status", 400));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    Map.of("error", "Lỗi máy chủ", "message", e.getMessage()));
        }
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<?> deleteEducation(@PathVariable Integer id) {
        try {
            educationService.deleteEducation(id);
            return ResponseEntity.ok(Map.of("message", "Xóa học vấn thành công"));
        } catch (NoFoundException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }
}
