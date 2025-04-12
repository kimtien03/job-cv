package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.Entity.Experience;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Service.ExperienceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Experience")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @GetMapping("/GetAllExperience")
    public ResponseEntity<?> getAllExperience() {
        try {
            List<Experience> experiences = experienceService.getAllExperience();
            return ResponseEntity.ok(experiences);
        } catch (NoFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/GetExperienceByID/{id}")
    public ResponseEntity<?> getExperienceById(@PathVariable Integer id) {
        try {
            Experience experience = experienceService.getExperienceById(id);
            return ResponseEntity.ok(experience);
        } catch (NoFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/Create")
    public ResponseEntity<?> createExperience(@RequestBody Experience experience) {
        try {
            Experience created = experienceService.createExperience(experience);
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
    public ResponseEntity<?> updateExperience(@PathVariable Integer id, @RequestBody Experience experience) {
        try {
            Experience updated = experienceService.updateExperience(id, experience);
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
    public ResponseEntity<?> deleteExperience(@PathVariable Integer id) {
        try {
            experienceService.deleteExperience(id);
            return ResponseEntity.ok(Map.of("message", "Xóa kinh nghiệm thành công"));
        } catch (NoFoundException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }
}
