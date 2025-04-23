package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.DTO.StyleSectionFieldsDTO;
import com.example.BEJobApplication.Entity.Style_section_fields;
import com.example.BEJobApplication.Entity.Sections;
import com.example.BEJobApplication.Entity.Styles;
import com.example.BEJobApplication.Entity.Fields;
import com.example.BEJobApplication.Mapper.StyleSectionFieldsMapper;

import com.example.BEJobApplication.Responsitory.FieldsRepository;
import com.example.BEJobApplication.Responsitory.SectionsRepository;
import com.example.BEJobApplication.Responsitory.Style_section_fieldsRepository;
import com.example.BEJobApplication.Responsitory.StylesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Style_section_fieldsService {

    @Autowired
    private Style_section_fieldsRepository styleSectionFieldsRepository;

    @Autowired
    private SectionsRepository sectionsRepository;

    @Autowired
    private StylesRepository stylesRepository;

    @Autowired
    private FieldsRepository fieldsRepository;

    // Create or Update a Style_section_field
    public StyleSectionFieldsDTO save(StyleSectionFieldsDTO dto) {
        // Find related entities (Section, Style, Field)
        Sections section = sectionsRepository.findById(dto.getSectionId())
                .orElseThrow(() -> new RuntimeException("Section not found with ID " + dto.getSectionId()));
        Styles style = stylesRepository.findById(dto.getStyleId())
                .orElseThrow(() -> new RuntimeException("Style not found with ID " + dto.getStyleId()));
        Fields field = fieldsRepository.findById(dto.getFieldId())
                .orElseThrow(() -> new RuntimeException("Field not found with ID " + dto.getFieldId()));

        // Map DTO to Entity
        Style_section_fields entity = StyleSectionFieldsMapper.toEntity(dto, section, style, field);

        // Save the entity to the repository
        Style_section_fields savedEntity = styleSectionFieldsRepository.save(entity);

        // Convert entity back to DTO
        return StyleSectionFieldsMapper.toDTO(savedEntity);
    }

    // Get a Style_section_field by ID
    public StyleSectionFieldsDTO getById(Integer id) {
        Style_section_fields entity = styleSectionFieldsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StyleSectionField not found with ID " + id));
        return StyleSectionFieldsMapper.toDTO(entity);
    }

    // Get all Style_section_fields
    public List<StyleSectionFieldsDTO> getAll() {
        List<Style_section_fields> entities = styleSectionFieldsRepository.findAll();
        return entities.stream()
                .map(StyleSectionFieldsMapper::toDTO)
                .toList();
    }

    // Delete a Style_section_field by ID
    public void delete(Integer id) {
        if (!styleSectionFieldsRepository.existsById(id)) {
            throw new RuntimeException("StyleSectionField not found with ID " + id);
        }
        styleSectionFieldsRepository.deleteById(id);
    }
}
