package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.Entity.Template_data;
import com.example.BEJobApplication.Service.Template_dataService;
import com.example.BEJobApplication.Exception.NoFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/template-data")
public class Template_dataController {

    @Autowired
    private Template_dataService templateDataService;

    // Lấy tất cả dữ liệu template
    @GetMapping
    public ResponseEntity<List<Template_data>> getAllTemplateData() {
        try {
            List<Template_data> dataList = templateDataService.getAllTemplateData();
            return new ResponseEntity<>(dataList, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Lấy dữ liệu template theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Template_data> getTemplateDataById(@PathVariable("id") Integer id) {
        try {
            Template_data templateData = templateDataService.getTemplateDataById(id);
            return new ResponseEntity<>(templateData, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Tạo mới dữ liệu template
    @PostMapping
    public ResponseEntity<Template_data> createTemplateData(@RequestBody Template_data templateData) {
        try {
            Template_data createdTemplateData = templateDataService.createTemplateData(templateData);
            return new ResponseEntity<>(createdTemplateData, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Cập nhật dữ liệu template
    @PutMapping("/{id}")
    public ResponseEntity<Template_data> updateTemplateData(@PathVariable("id") Integer id, @RequestBody Template_data templateDataDetails) {
        try {
            Template_data updatedTemplateData = templateDataService.updateTemplateData(id, templateDataDetails);
            return new ResponseEntity<>(updatedTemplateData, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Xóa dữ liệu template
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTemplateData(@PathVariable("id") Integer id) {
        try {
            templateDataService.deleteTemplateData(id);
            return new ResponseEntity<>("Dữ liệu template đã được xóa thành công.", HttpStatus.NO_CONTENT);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
