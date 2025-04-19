package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.Entity.Template_cvs;
import com.example.BEJobApplication.Service.Template_cvsService;
import com.example.BEJobApplication.Exception.NoFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/api/template-cvs")
public class Template_cvsController {

    @Autowired
    private Template_cvsService templateCvsService;

    // Lấy tất cả template_cvs
    @GetMapping
    public ResponseEntity<List<Template_cvs>> getAllTemplateCvs() {
        try {
            List<Template_cvs> templateCvsList = templateCvsService.getAllTemplateCvs();
            return new ResponseEntity<>(templateCvsList, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Lấy template_cvs theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Template_cvs> getTemplateCvsById(@PathVariable("id") Integer id) {
        try {
            Template_cvs templateCvs = templateCvsService.getTemplateCvsById(id);
            return new ResponseEntity<>(templateCvs, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Tạo mới template_cvs
    @PostMapping
    public ResponseEntity<Template_cvs> createTemplateCvs(@RequestBody Template_cvs templateCvs) {
        try {
            Template_cvs createdTemplateCvs = templateCvsService.createTemplateCvs(templateCvs);
            return new ResponseEntity<>(createdTemplateCvs, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Cập nhật template_cvs
    @PutMapping("/{id}")
    public ResponseEntity<Template_cvs> updateTemplateCvs(@PathVariable("id") Integer id, @RequestBody Template_cvs templateCvsDetails) {
        try {
            Template_cvs updatedTemplateCvs = templateCvsService.updateTemplateCvs(id, templateCvsDetails);
            return new ResponseEntity<>(updatedTemplateCvs, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Xóa template_cvs
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTemplateCvs(@PathVariable("id") Integer id) {
        try {
            templateCvsService.deleteTemplateCvs(id);
            return new ResponseEntity<>("Template CV đã được xóa thành công.", HttpStatus.NO_CONTENT);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/filter")
    public ResponseEntity<?> filterBySectionAndStyle(
            @RequestParam("position_id") Integer position_id,
            @RequestParam("style_id") Integer style_id
    ) {
        try {
            List<Template_cvs> templates = templateCvsService.findByPositionIdAndStyleId(position_id, style_id);

            if (templates.isEmpty()) {
                return new ResponseEntity<>("Không tìm thấy dữ liệu phù hợp", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(templates, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Lỗi server: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
