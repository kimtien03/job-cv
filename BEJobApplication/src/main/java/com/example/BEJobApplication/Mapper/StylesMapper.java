package com.example.BEJobApplication.Mapper;

import com.example.BEJobApplication.DTO.StylesDTO;
import com.example.BEJobApplication.Entity.Styles;

public class StylesMapper {

    // Entity -> DTO
    public static StylesDTO toDTO(Styles style) {
        if (style == null) return null;

        StylesDTO dto = new StylesDTO();
        dto.setId(style.getId());
        dto.setStyleName(style.getStyle_name());
        dto.setFileName(style.getFile_name());
        dto.setCssName(style.getCss_name());

        return dto;
    }

    // DTO -> Entity
    public static Styles toEntity(StylesDTO dto) {
        if (dto == null) return null;

        Styles style = new Styles();
        style.setId(dto.getId());
        style.setStyle_name(dto.getStyleName());
        style.setFile_name(dto.getFileName());
        style.setCss_name(dto.getCssName());

        return style;
    }
}
