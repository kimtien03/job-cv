package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.DTO.TemplateDataDTO;
import com.example.BEJobApplication.Service.Template_dataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/template-data")
public class Template_dataController {

    private final Template_dataService templateDataService;

    @Autowired
    public Template_dataController(Template_dataService templateDataService) {
        this.templateDataService = templateDataService;
    }

    // Lấy tất cả TemplateData
    @GetMapping
    public ResponseEntity<List<TemplateDataDTO>> getAllTemplateData() {
        List<TemplateDataDTO> templateDataList = templateDataService.getAllTemplateData();
        return new ResponseEntity<>(templateDataList, HttpStatus.OK);
    }

    // Lấy thông tin TemplateData theo ID
    @GetMapping("/{id}")
    public ResponseEntity<TemplateDataDTO> getTemplateDataById(@PathVariable Integer id) {
        TemplateDataDTO templateData = templateDataService.getById(id);
        return new ResponseEntity<>(templateData, HttpStatus.OK);
    }

    // Tạo mới TemplateData
    @PostMapping
    public ResponseEntity<TemplateDataDTO> createTemplateData(@RequestBody TemplateDataDTO templateDataDTO) {
        TemplateDataDTO savedTemplateData = templateDataService.createTemplateData(templateDataDTO);
        return new ResponseEntity<>(savedTemplateData, HttpStatus.CREATED);
    }

    // Cập nhật TemplateData theo ID
    @PutMapping("/{id}")
    public ResponseEntity<TemplateDataDTO> updateTemplateData(@PathVariable Integer id, @RequestBody TemplateDataDTO templateDataDTO) {
        TemplateDataDTO updatedTemplateData = templateDataService.updateTemplateData(id, templateDataDTO);
        return new ResponseEntity<>(updatedTemplateData, HttpStatus.OK);
    }

    // Xóa TemplateData theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplateData(@PathVariable Integer id) {
        templateDataService.deleteTemplateData(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
