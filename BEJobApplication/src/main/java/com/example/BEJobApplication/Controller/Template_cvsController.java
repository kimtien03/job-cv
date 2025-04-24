package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.DTO.TemplateCvsDTO;
import com.example.BEJobApplication.Service.Template_cvsService;
import com.example.BEJobApplication.Exception.NoFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/template-cvs")
public class Template_cvsController {

    @Autowired
    private Template_cvsService template_cvsService;

    // Lấy tất cả TemplateCVs
    @GetMapping
    public List<TemplateCvsDTO> getAllTemplateCvs() {
        return template_cvsService.getAllTemplateCvs();
    }

    // Lấy TemplateCVs theo PositionId và StyleId
    @GetMapping("/search")
    public ResponseEntity<?> getTemplateByPositionAndStyle(
            @RequestParam(value = "positionId", required = false) Integer positionId,
            @RequestParam(value = "styleId", required = false) Integer styleId) {

        try {
            // Gọi service để lấy dữ liệu
            List<TemplateCvsDTO> templates = template_cvsService.getTemplateByPositionAndStyle(positionId, styleId);
            return new ResponseEntity<>(templates, HttpStatus.OK);  // Trả về danh sách templates nếu không có lỗi
        } catch (NoFoundException ex) {
            // Nếu có lỗi NoFoundException, trả về lỗi 404
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException ex) {
            // Nếu có lỗi IllegalArgumentException (ví dụ: tham số không hợp lệ)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            // Bắt tất cả các lỗi khác và trả về lỗi 500
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Tạo mới TemplateCVs
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TemplateCvsDTO createTemplateCvs(@RequestBody TemplateCvsDTO dto) {
        return template_cvsService.createTemplateCvs(dto);
    }

    // Cập nhật TemplateCVs theo ID
    @PutMapping("/{id}")
    public TemplateCvsDTO updateTemplateCvs(@PathVariable Integer id, @RequestBody TemplateCvsDTO dto) {
        return template_cvsService.updateTemplateCvs(id, dto);
    }

    // Xóa TemplateCVs theo ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTemplateCvs(@PathVariable Integer id) {
        template_cvsService.deleteTemplateCvs(id);
    }

}
