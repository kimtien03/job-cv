package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.Entity.Industries;
import com.example.BEJobApplication.Service.IndustriesService;
import com.example.BEJobApplication.Exception.NoFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/industries")
public class IndustriesController {

    @Autowired
    private IndustriesService industriesService;

    // Lấy tất cả các ngành
    @GetMapping
    public ResponseEntity<List<Industries>> getAllIndustries() {
        try {
            List<Industries> industriesList = industriesService.getAllIndustries();
            return new ResponseEntity<>(industriesList, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Lấy ngành theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Industries> getIndustryById(@PathVariable("id") Integer id) {
        try {
            Industries industry = industriesService.getIndustryById(id);
            return new ResponseEntity<>(industry, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Tạo mới ngành
    @PostMapping
    public ResponseEntity<Industries> createIndustry(@RequestBody Industries industry) {
        try {
            Industries createdIndustry = industriesService.createIndustry(industry);
            return new ResponseEntity<>(createdIndustry, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Cập nhật ngành
    @PutMapping("/{id}")
    public ResponseEntity<Industries> updateIndustry(@PathVariable("id") Integer id,
                                                     @RequestBody Industries updatedIndustry) {
        try {
            Industries updated = industriesService.updateIndustry(id, updatedIndustry);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Xóa ngành theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIndustry(@PathVariable("id") Integer id) {
        try {
            industriesService.deleteIndustry(id);
            return new ResponseEntity<>("Ngành đã được xóa thành công.", HttpStatus.NO_CONTENT);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
