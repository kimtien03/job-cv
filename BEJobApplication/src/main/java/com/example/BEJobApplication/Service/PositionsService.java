package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.Entity.Positions;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Responsitory.PositionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionsService {

    @Autowired
    private PositionsRepository positionsRepository;

    // Lấy tất cả các vị trí
    public List<Positions> getAllPositions() {
        List<Positions> positionsList = positionsRepository.findAll();
        if (positionsList.isEmpty()) {
            throw new NoFoundException("Không có vị trí nào trong hệ thống.");
        }
        return positionsList;
    }

    // Lấy một vị trí theo ID
    public Positions getPositionById(Integer id) {
        return positionsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy vị trí với ID: " + id));
    }

    // Lấy một vị trí theo IndustryID
    public List<Positions> getPositionByIndustryId(Integer industryId) {
        List<Positions> positions = positionsRepository.findByIndustryId(industryId);
        if (positions.isEmpty()) {
            throw new NoFoundException("Không tìm thấy vị trí nào thuộc ngành ID: " + industryId);
        }
        return positions;
    }

    // Tạo mới một vị trí
    public Positions createPosition(Positions position) {
        return positionsRepository.save(position);
    }

    // Cập nhật vị trí
    public Positions updatePosition(Integer id, Positions positionDetails) {
        Positions existingPosition = positionsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy vị trí với ID: " + id));

        // Cập nhật các trường của position
        existingPosition.setName(positionDetails.getName());
        existingPosition.setIndustry_id(positionDetails.getIndustry_id());

        return positionsRepository.save(existingPosition);
    }

    // Xóa một vị trí theo ID
    public void deletePosition(Integer id) {
        if (!positionsRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy vị trí với ID: " + id);
        }
        positionsRepository.deleteById(id);
    }
}
