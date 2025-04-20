package com.example.BEJobApplication.Mapper;

import com.example.BEJobApplication.DTO.StyleSectionFieldsDTO;
import com.example.BEJobApplication.Entity.Style_section_fields;
import com.example.BEJobApplication.Entity.Sections;
import com.example.BEJobApplication.Entity.Styles;
import com.example.BEJobApplication.Entity.Fields;

public class StyleSectionFieldsMapper {

    // Convert from Entity to DTO
    public static StyleSectionFieldsDTO toDTO(Style_section_fields entity) {
        if (entity == null) {
            return null;
        }

        return new StyleSectionFieldsDTO(
                entity.getId(),
                entity.getSection() != null ? entity.getSection().getId() : null,
                entity.getStyle() != null ? entity.getStyle().getId() : null,
                entity.getField() != null ? entity.getField().getId() : null,
                entity.getVisible()
        );
    }

    // Convert from DTO to Entity
    public static Style_section_fields toEntity(StyleSectionFieldsDTO dto, Sections section, Styles style, Fields field) {
        if (dto == null) {
            return null;
        }

        return new Style_section_fields(
                dto.getId(),
                section,
                style,
                field,
                dto.getVisible()
        );
    }
}
