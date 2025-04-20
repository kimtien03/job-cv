package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.DTO.IndustriesDTO;
import com.example.BEJobApplication.Entity.Industries;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Mapper.IndustriesMapper;
import com.example.BEJobApplication.Responsitory.IndustriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndustriesService {

    private final IndustriesRepository industriesRepository;

    @Autowired
    public IndustriesService(IndustriesRepository industriesRepository) {
        this.industriesRepository = industriesRepository;
    }

    // Lấy tất cả các ngành
    public List<IndustriesDTO> getAllIndustries() {
        List<Industries> industries = industriesRepository.findAll();
        return industries.stream()
                .map(IndustriesMapper::toIndustriesDTO)
                .collect(Collectors.toList());
    }

    // Lấy ngành theo ID
    public IndustriesDTO getIndustryById(Integer id) {
        Industries industries = industriesRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy ngành với ID: " + id));
        return IndustriesMapper.toIndustriesDTO(industries);
    }

    // Tạo mới một ngành
    public IndustriesDTO createIndustry(IndustriesDTO industriesDTO) {
        Industries industry = IndustriesMapper.toIndustriesEntity(industriesDTO);
        industry = industriesRepository.save(industry);
        return IndustriesMapper.toIndustriesDTO(industry);
    }

    // Cập nhật ngành theo ID
    public IndustriesDTO updateIndustry(Integer id, IndustriesDTO industriesDTO) {
        Industries existingIndustry = industriesRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy ngành với ID: " + id));

        // Cập nhật thông tin ngành
        existingIndustry.setName(industriesDTO.getName());

        // Lưu lại thông tin đã cập nhật
        existingIndustry = industriesRepository.save(existingIndustry);
        return IndustriesMapper.toIndustriesDTO(existingIndustry);
    }

    // Xóa ngành theo ID
    public void deleteIndustry(Integer id) {
        Industries existingIndustry = industriesRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy ngành với ID: " + id));
        industriesRepository.delete(existingIndustry);
    }
}
