package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.DTO.IndustriesDTO;
import com.example.BEJobApplication.Service.IndustriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/industries")
public class IndustriesController {

    private final IndustriesService industriesService;

    @Autowired
    public IndustriesController(IndustriesService industriesService) {
        this.industriesService = industriesService;
    }

    // Lấy tất cả các ngành
    @GetMapping
    public ResponseEntity<List<IndustriesDTO>> getAllIndustries() {
        List<IndustriesDTO> industries = industriesService.getAllIndustries();
        return new ResponseEntity<>(industries, HttpStatus.OK);
    }

    // Lấy ngành theo ID
    @GetMapping("/{id}")
    public ResponseEntity<IndustriesDTO> getIndustryById(@PathVariable Integer id) {
        IndustriesDTO industry = industriesService.getIndustryById(id);
        return new ResponseEntity<>(industry, HttpStatus.OK);
    }

    // Tạo mới ngành
    @PostMapping
    public ResponseEntity<IndustriesDTO> createIndustry(@RequestBody IndustriesDTO industriesDTO) {
        IndustriesDTO createdIndustry = industriesService.createIndustry(industriesDTO);
        return new ResponseEntity<>(createdIndustry, HttpStatus.CREATED);
    }

    // Cập nhật ngành theo ID
    @PutMapping("/{id}")
    public ResponseEntity<IndustriesDTO> updateIndustry(@PathVariable Integer id, @RequestBody IndustriesDTO industriesDTO) {
        IndustriesDTO updatedIndustry = industriesService.updateIndustry(id, industriesDTO);
        return new ResponseEntity<>(updatedIndustry, HttpStatus.OK);
    }

    // Xóa ngành theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIndustry(@PathVariable Integer id) {
        industriesService.deleteIndustry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
