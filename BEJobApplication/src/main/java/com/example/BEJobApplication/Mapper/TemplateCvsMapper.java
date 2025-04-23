package com.example.BEJobApplication.Mapper;

import com.example.BEJobApplication.DTO.TemplateCvsDTO;
import com.example.BEJobApplication.Entity.Template_cvs;
import com.example.BEJobApplication.Entity.Positions;
import com.example.BEJobApplication.Entity.Styles;

public class TemplateCvsMapper {

    public static TemplateCvsDTO toDTO(Template_cvs templateCvs) {
        if (templateCvs == null) {
            return null;
        }

        TemplateCvsDTO dto = new TemplateCvsDTO();
        dto.setId(templateCvs.getId());

        // Nếu có, gán các ID của các mối quan hệ
        dto.setPositionId(templateCvs.getPositions() != null ? templateCvs.getPositions().getId() : null);
        dto.setStyleId(templateCvs.getStyle() != null ? templateCvs.getStyle().getId() : null);
        dto.setImage(templateCvs.getImage());
        return dto;
    }

    public static Template_cvs toEntity(TemplateCvsDTO dto, Positions positions, Styles style) {
        if (dto == null) {
            return null;
        }

        Template_cvs entity = new Template_cvs();
        entity.setId(dto.getId());
        entity.setPositions(positions);
        entity.setStyle(style);
        entity.setImage(dto.getImage());

        return entity;
    }
}
