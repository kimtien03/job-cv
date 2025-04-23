package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.DTO.PositionsDTO;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Service.PositionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionsController {

    private final PositionsService positionsService;

    @Autowired
    public PositionsController(PositionsService positionsService) {
        this.positionsService = positionsService;
    }

    // Lấy tất cả các vị trí
    @GetMapping
    public ResponseEntity<List<PositionsDTO>> getAllPositions() {
        List<PositionsDTO> positions = positionsService.getAllPositions();
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }

    // Lấy vị trí theo ID
    @GetMapping("/{id}")
    public ResponseEntity<PositionsDTO> getPositionById(@PathVariable Integer id) {
        PositionsDTO position = positionsService.getPositionById(id);
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    // Lấy vị trí theo IndustryID
    @GetMapping("/getPositionByIndustryId")
    public ResponseEntity<List<PositionsDTO>> getPositionByIndustryId(@RequestParam Integer industryId) {
        try {
            List<PositionsDTO> positionsList = positionsService.getPositionByIndustryId(industryId);
            return new ResponseEntity<>(positionsList, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Tạo mới vị trí
    @PostMapping
    public ResponseEntity<PositionsDTO> createPosition(@RequestBody PositionsDTO positionsDTO) {
        PositionsDTO createdPosition = positionsService.createPosition(positionsDTO);
        return new ResponseEntity<>(createdPosition, HttpStatus.CREATED);
    }

    // Cập nhật vị trí theo ID
    @PutMapping("/{id}")
    public ResponseEntity<PositionsDTO> updatePosition(@PathVariable Integer id, @RequestBody PositionsDTO positionsDTO) {
        PositionsDTO updatedPosition = positionsService.updatePosition(id, positionsDTO);
        return new ResponseEntity<>(updatedPosition, HttpStatus.OK);
    }

    // Xóa vị trí theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable Integer id) {
        positionsService.deletePosition(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
