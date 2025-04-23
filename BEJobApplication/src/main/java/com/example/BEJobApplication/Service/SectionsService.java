package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.DTO.SectionsDTO;
import com.example.BEJobApplication.Entity.Sections;
import com.example.BEJobApplication.Mapper.SectionsMapper;
import com.example.BEJobApplication.Responsitory.SectionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionsService {

    @Autowired
    private SectionsRepository sectionsRepository;

    // Lấy tất cả các section
    public List<SectionsDTO> getAllSections() {
        List<Sections> sectionsList = sectionsRepository.findAll();
        return sectionsList.stream()
                .map(SectionsMapper::toSectionsDTO)
                .collect(Collectors.toList());
    }

    // Lấy thông tin Section theo ID
    public SectionsDTO getSectionById(Integer id) {
        Sections section = sectionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Section với ID: " + id));
        return SectionsMapper.toSectionsDTO(section);
    }

    // Tạo mới Section từ DTO
    public SectionsDTO createSection(SectionsDTO sectionsDTO) {
        // Chuyển đổi từ DTO sang Entity
        Sections section = SectionsMapper.toSectionsEntity(sectionsDTO);
        // Lưu vào cơ sở dữ liệu
        section = sectionsRepository.save(section);
        // Trả về DTO sau khi lưu
        return SectionsMapper.toSectionsDTO(section);
    }

    // Cập nhật Section
    public SectionsDTO updateSection(Integer id, SectionsDTO sectionsDTO) {
        // Tìm Section theo ID
        Sections existingSection = sectionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Section với ID: " + id));

        // Cập nhật thông tin Section
        existingSection.setSections_key(sectionsDTO.getSectionsKey());
        existingSection.setSection_name(sectionsDTO.getSectionName());

        // Lưu lại Section đã được cập nhật
        existingSection = sectionsRepository.save(existingSection);

        // Trả về DTO của Section đã được cập nhật
        return SectionsMapper.toSectionsDTO(existingSection);
    }

    // Xóa Section theo ID
    public void deleteSection(Integer id) {
        // Kiểm tra nếu Section tồn tại, nếu không thì throw exception
        Sections section = sectionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Section với ID: " + id));

        // Xóa Section
        sectionsRepository.delete(section);
    }
}
