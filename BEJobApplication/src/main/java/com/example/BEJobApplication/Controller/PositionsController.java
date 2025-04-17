package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.Entity.Positions;
import com.example.BEJobApplication.Service.PositionsService;
import com.example.BEJobApplication.Exception.NoFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionsController {

    @Autowired
    private PositionsService positionsService;

    // Lấy tất cả các vị trí
    @GetMapping
    public ResponseEntity<List<Positions>> getAllPositions() {
        try {
            List<Positions> positionsList = positionsService.getAllPositions();
            return new ResponseEntity<>(positionsList, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Lấy vị trí theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Positions> getPositionById(@PathVariable("id") Integer id) {
        try {
            Positions position = positionsService.getPositionById(id);
            return new ResponseEntity<>(position, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Tạo mới một vị trí
    @PostMapping
    public ResponseEntity<Positions> createPosition(@RequestBody Positions position) {
        try {
            Positions createdPosition = positionsService.createPosition(position);
            return new ResponseEntity<>(createdPosition, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Cập nhật vị trí
    @PutMapping("/{id}")
    public ResponseEntity<Positions> updatePosition(@PathVariable("id") Integer id,
                                                    @RequestBody Positions positionDetails) {
        try {
            Positions updatedPosition = positionsService.updatePosition(id, positionDetails);
            return new ResponseEntity<>(updatedPosition, HttpStatus.OK);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Xóa một vị trí theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePosition(@PathVariable("id") Integer id) {
        try {
            positionsService.deletePosition(id);
            return new ResponseEntity<>("Vị trí đã được xóa thành công.", HttpStatus.NO_CONTENT);
        } catch (NoFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
