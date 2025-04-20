package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.DTO.TemplateCvsDTO;
import com.example.BEJobApplication.Service.Template_cvsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/template-cvs")
public class Template_cvsController {

    private final Template_cvsService templateCvsService;

    @Autowired
    public Template_cvsController(Template_cvsService templateCvsService) {
        this.templateCvsService = templateCvsService;
    }

    // Tạo hoặc cập nhật Template CV
    @PostMapping
    public ResponseEntity<TemplateCvsDTO> saveTemplateCvs(@RequestBody TemplateCvsDTO templateCvsDTO) {
        TemplateCvsDTO savedTemplateCvs = templateCvsService.saveTemplateCvs(templateCvsDTO);
        return new ResponseEntity<>(savedTemplateCvs, HttpStatus.CREATED);
    }

    // Lấy thông tin Template CV theo ID
    @GetMapping("/{id}")
    public ResponseEntity<TemplateCvsDTO> getTemplateCvsById(@PathVariable Integer id) {
        TemplateCvsDTO templateCvs = templateCvsService.getTemplateCvsById(id);
        return new ResponseEntity<>(templateCvs, HttpStatus.OK);
    }
}
