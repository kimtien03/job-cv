package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.DTO.TemplateCvsDTO;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Service.Template_cvsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/template-cvs")
public class Template_cvsController {
    private static final int TEMPLATES_PER_PAGE = 6;
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

    @GetMapping()
    public ResponseEntity<?> getTemplateCvs(
            @RequestParam(value = "industryId", required = false) Integer industryId,
            @RequestParam(value = "positionId", required = false) Integer positionId,
            @RequestParam(value = "styleId", required = false) Integer styleId,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page
    ) {
        try {
            Pageable pageable = PageRequest.of(page-1, TEMPLATES_PER_PAGE);
            Page<TemplateCvsDTO> result = templateCvsService.filterTemplates(industryId, positionId, styleId, pageable);
            return ResponseEntity.ok(result);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>("Not Found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("Lỗi server: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
