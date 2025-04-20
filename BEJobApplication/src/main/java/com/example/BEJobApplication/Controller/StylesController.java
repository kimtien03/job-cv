package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.DTO.StylesDTO;
import com.example.BEJobApplication.Service.StylesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/styles")
public class StylesController {

    private final StylesService stylesService;

    @Autowired
    public StylesController(StylesService stylesService) {
        this.stylesService = stylesService;
    }

    // Lấy tất cả các style
    @GetMapping
    public ResponseEntity<List<StylesDTO>> getAllStyles() {
        List<StylesDTO> styles = stylesService.getAllStyles();
        return new ResponseEntity<>(styles, HttpStatus.OK);
    }

    // Lấy thông tin style theo ID
    @GetMapping("/{id}")
    public ResponseEntity<StylesDTO> getStyleById(@PathVariable Integer id) {
        StylesDTO style = stylesService.getStyleById(id);
        return new ResponseEntity<>(style, HttpStatus.OK);
    }

    // Thêm mới một style
    @PostMapping
    public ResponseEntity<StylesDTO> createStyle(@RequestBody StylesDTO stylesDTO) {
        StylesDTO createdStyle = stylesService.createStyle(stylesDTO);
        return new ResponseEntity<>(createdStyle, HttpStatus.CREATED);
    }

    // Cập nhật thông tin một style theo ID
    @PutMapping("/{id}")
    public ResponseEntity<StylesDTO> updateStyle(@PathVariable Integer id, @RequestBody StylesDTO stylesDTO) {
        StylesDTO updatedStyle = stylesService.updateStyle(id, stylesDTO);
        return new ResponseEntity<>(updatedStyle, HttpStatus.OK);
    }

    // Xóa một style theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStyle(@PathVariable Integer id) {
        stylesService.deleteStyle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
