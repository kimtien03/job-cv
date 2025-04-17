package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.Entity.Styles;
import com.example.BEJobApplication.Service.StylesService;
import com.example.BEJobApplication.Exception.NoFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/styles")
public class StylesController {

    @Autowired
    private StylesService stylesService;

    // Lấy tất cả styles
    @GetMapping
    public ResponseEntity<List<Styles>> getAllStyles() {
        try {
            List<Styles> stylesList = stylesService.getAllStyles();
            return new ResponseEntity<>(stylesList, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Lấy style theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Styles> getStyleById(@PathVariable("id") Integer id) {
        try {
            Styles style = stylesService.getStyleById(id);
            return new ResponseEntity<>(style, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Tạo mới style
    @PostMapping
    public ResponseEntity<Styles> createStyle(@RequestBody Styles style) {
        try {
            Styles createdStyle = stylesService.createStyle(style);
            return new ResponseEntity<>(createdStyle, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Cập nhật style
    @PutMapping("/{id}")
    public ResponseEntity<Styles> updateStyle(@PathVariable("id") Integer id, @RequestBody Styles updatedStyle) {
        try {
            Styles style = stylesService.updateStyle(id, updatedStyle);
            return new ResponseEntity<>(style, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Xóa style
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStyle(@PathVariable("id") Integer id) {
        try {
            stylesService.deleteStyle(id);
            return new ResponseEntity<>("Style đã được xóa thành công.", HttpStatus.NO_CONTENT);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
