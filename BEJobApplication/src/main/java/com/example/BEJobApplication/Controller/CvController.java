package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.Entity.Cv;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Cv")
public class CvController {

    @Autowired
    private CvService cvService;

    @GetMapping("/GetAllCv")
    public ResponseEntity<?> getAllCv() {
        try {
            List<Cv> cvs = cvService.getAllCV();
            return ResponseEntity.ok(cvs);
        } catch (NoFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/GetCvByID/{id}")
    public ResponseEntity<?> getCvById(@PathVariable Integer id) {
        try {
            Cv cv = cvService.getCvById(id);
            return ResponseEntity.ok(cv);
        } catch (NoFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/Create")
    public ResponseEntity<?> createCv(@RequestBody Cv cv) {
        try {
            Cv created = cvService.createCv(cv);
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
    public ResponseEntity<?> updateCv(@PathVariable Integer id, @RequestBody Cv cv) {
        try {
            Cv updated = cvService.updateCv(id, cv);
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
    public ResponseEntity<?> deleteCv(@PathVariable Integer id) {
        try {
            cvService.deleteCv(id);
            return ResponseEntity.ok(Map.of("message", "Xóa CV thành công"));
        } catch (NoFoundException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }
}
