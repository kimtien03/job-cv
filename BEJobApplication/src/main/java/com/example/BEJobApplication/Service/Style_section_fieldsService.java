package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.Entity.Style_section_fields;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Responsitory.Style_section_fieldsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Style_section_fieldsService {

    @Autowired
    private Style_section_fieldsRepository styleSectionFieldsRepository;

    // Lấy tất cả dữ liệu style_section_fields
    public List<Style_section_fields> getAllStyleSectionFields() {
        List<Style_section_fields> list = styleSectionFieldsRepository.findAll();
        if (list.isEmpty()) {
            throw new NoFoundException("Không có dữ liệu style_section_fields.");
        }
        return list;
    }

    // Lấy dữ liệu theo ID
    public Style_section_fields getById(Integer id) {
        return styleSectionFieldsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy style_section_field với id: " + id));
    }

    // Tạo mới style_section_field
    public Style_section_fields create(Style_section_fields data) {
        return styleSectionFieldsRepository.save(data);
    }

    // Cập nhật style_section_field
    public Style_section_fields update(Integer id, Style_section_fields updated) {
        // Retrieve the existing object
        Style_section_fields existing = styleSectionFieldsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy style_section_field với id: " + id));

        // Set the updated fields
        existing.setSection_id(updated.getSection_id());
        existing.setStyle_id(updated.getStyle_id());
        existing.setField_id(updated.getField_id());
        existing.setVisible(updated.getVisible());

        // Save the updated object
        return styleSectionFieldsRepository.save(existing);
    }

    // Xóa style_section_field
    public void delete(Integer id) {
        if (!styleSectionFieldsRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy style_section_field với id: " + id);
        }
        styleSectionFieldsRepository.deleteById(id);
    }
}
