package com.example.BEJobApplication.Mapper;

import com.example.BEJobApplication.DTO.TemplateDataDTO;
import com.example.BEJobApplication.Entity.Template_data;
import com.example.BEJobApplication.Entity.Template_cvs;
import com.example.BEJobApplication.Entity.Sections;
import com.example.BEJobApplication.Entity.Fields;

public class TemplateDataMapper {

    // Entity → DTO
    public static TemplateDataDTO toDTO(Template_data entity) {
        if (entity == null) return null;

        TemplateDataDTO dto = new TemplateDataDTO();
        dto.setId(entity.getId());
        dto.setTemplateCvId(entity.getTemplateCvs().getId());
        dto.setSectionId(entity.getSection().getId());
        dto.setFieldId(entity.getField().getId());
        dto.setValue(entity.getValue());
        dto.setGroupIndex(entity.getGroupIndex());

        return dto;
    }

    // DTO → Entity (chỉ dùng khi tạo mới, cần truyền các entity con sẵn từ service)
    public static Template_data toEntity(TemplateDataDTO dto, Template_cvs templateCvs, Sections section, Fields field) {
        if (dto == null || templateCvs == null || section == null || field == null) return null;

        Template_data entity = new Template_data();
        entity.setId(dto.getId()); // Nếu tạo mới có thể bỏ qua
        entity.setTemplateCvs(templateCvs);
        entity.setSection(section);
        entity.setField(field);
        entity.setValue(dto.getValue());
        entity.setGroupIndex(dto.getGroupIndex());

        return entity;
    }
}
