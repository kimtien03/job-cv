package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.DTO.PositionsDTO;
import com.example.BEJobApplication.Entity.Positions;
import com.example.BEJobApplication.Entity.Industries;
import com.example.BEJobApplication.Mapper.PositionsMapper;

import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Responsitory.IndustriesRepository;
import com.example.BEJobApplication.Responsitory.PositionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PositionsService {

    @Autowired
    private PositionsRepository positionsRepository;

    @Autowired
    private IndustriesRepository industriesRepository;

    // Lấy tất cả các vị trí
    public List<PositionsDTO> getAllPositions() {
        List<Positions> positions = positionsRepository.findAll();
        return positions.stream()
                .map(PositionsMapper::toPositionsDTO)
                .collect(Collectors.toList());
    }

    // Lấy vị trí theo ID
    public PositionsDTO getPositionById(Integer id) {
        Positions position = positionsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy vị trí với ID: " + id));
        return PositionsMapper.toPositionsDTO(position);
    }

    // Tạo mới một vị trí
    public PositionsDTO createPosition(PositionsDTO positionsDTO) {
        Industries industry = industriesRepository.findById(positionsDTO.getIndustryId())
                .orElseThrow(() -> new NoFoundException("Không tìm thấy ngành với ID: " + positionsDTO.getIndustryId()));

        Positions position = PositionsMapper.toPositionsEntity(positionsDTO, industry);
        position = positionsRepository.save(position);
        return PositionsMapper.toPositionsDTO(position);
    }

    // Cập nhật một vị trí
    public PositionsDTO updatePosition(Integer id, PositionsDTO positionsDTO) {
        Positions existingPosition = positionsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy vị trí với ID: " + id));

        Industries industry = industriesRepository.findById(positionsDTO.getIndustryId())
                .orElseThrow(() -> new NoFoundException("Không tìm thấy ngành với ID: " + positionsDTO.getIndustryId()));

        existingPosition.setName(positionsDTO.getName());
        existingPosition.setIndustry(industry);

        existingPosition = positionsRepository.save(existingPosition);
        return PositionsMapper.toPositionsDTO(existingPosition);
    }

    // Xóa một vị trí theo ID
    public void deletePosition(Integer id) {
        Positions existingPosition = positionsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy vị trí với ID: " + id));
        positionsRepository.delete(existingPosition);
    }
}
