package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.Entity.Template;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Service.TemplateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @GetMapping("/GetAllTemplate")
    public ResponseEntity<?> getAllTemplates() {
        try {
            List<Template> templates = templateService.getAllTemplates();
            return ResponseEntity.ok(templates);
        } catch (NoFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/GetTemplateByID/{id}")
    public ResponseEntity<?> getTemplateById(@PathVariable Integer id) {
        try {
            Template template = templateService.getTemplateById(id);
            return ResponseEntity.ok(template);
        } catch (NoFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/Create")
    public ResponseEntity<?> createTemplate(@RequestBody Template template) {
        try {
            Template createdTemplate = templateService.createTemplate(template);
            return ResponseEntity.status(200).body(createdTemplate);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", e.getMessage(), "status", 400));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    Map.of("error", "Lỗi máy chủ", "message", e.getMessage()));
        }
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<?> deleteTemplate(@PathVariable Integer id) {
        try {
            templateService.deleteTemplate(id);
            return ResponseEntity.ok(Map.of("message", "Xóa mẫu CV thành công"));
        } catch (NoFoundException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/Update/{id}")
    public ResponseEntity<?> updateTemplate(@PathVariable Integer id, @RequestBody Template template) {
        try {
            Template updatedTemplate = templateService.updateTemplate(id, template);
            return ResponseEntity.status(200).body(updatedTemplate);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", e.getMessage(), "status", 400));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    Map.of("error", "Lỗi máy chủ", "message", e.getMessage()));
        }
    }
}
