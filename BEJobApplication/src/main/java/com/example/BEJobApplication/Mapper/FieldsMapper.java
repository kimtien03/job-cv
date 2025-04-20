package com.example.BEJobApplication.Mapper;

import com.example.BEJobApplication.DTO.FieldsDTO;
import com.example.BEJobApplication.Entity.Fields;
import com.example.BEJobApplication.Entity.Sections;

public class FieldsMapper {

    // Chuyển từ Entity -> DTO
    public static FieldsDTO toFieldsDTO(Fields fields) {
        if (fields == null) return null;

        // Chuyển đổi thành DTO và trả về
        return new FieldsDTO(
                fields.getId(),
                fields.getSection() != null ? fields.getSection().getId() : null,  // Chỉ lấy ID của Section
                fields.getField_name(),
                fields.getField_type()
        );
    }

    // Chuyển từ DTO -> Entity
    public static Fields toFieldsEntity(FieldsDTO fieldsDTO, Sections section) {
        if (fieldsDTO == null) return null;

        // Chuyển đổi từ DTO sang Entity
        return new Fields(
                fieldsDTO.getId(),
                section,  // Cần phải lấy Entity Section tương ứng
                fieldsDTO.getFieldName(),
                fieldsDTO.getFieldType()
        );
    }
}
