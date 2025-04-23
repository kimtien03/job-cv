package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.Entity.Sections;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Responsitory.SectionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionsService {

    @Autowired
    private SectionsRepository sectionsRepository;

    // Lấy tất cả dữ liệu Sections
    public List<Sections> getAllSections() {
        List<Sections> sectionsList = sectionsRepository.findAll();
        if (sectionsList.isEmpty()) {
            throw new NoFoundException("Không có dữ liệu section nào.");
        }
        return sectionsList;
    }

    // Lấy một section theo ID
    public Sections getSectionById(Integer id) {
        return sectionsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy section với ID: " + id));
    }

    // Tạo mới một Section
    public Sections createSection(Sections section) {
        return sectionsRepository.save(section);
    }

    // Cập nhật một Section
    public Sections updateSection(Integer id, Sections sectionDetails) {
        Sections existingSection = sectionsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy section với ID: " + id));

        // Cập nhật các trường của section
        existingSection.setSections_key(sectionDetails.getSections_key());
        existingSection.setSection_name(sectionDetails.getSection_name());

        return sectionsRepository.save(existingSection);
    }

    // Xóa một Section theo ID
    public void deleteSection(Integer id) {
        if (!sectionsRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy section với ID: " + id);
        }
        sectionsRepository.deleteById(id);
    }
}
