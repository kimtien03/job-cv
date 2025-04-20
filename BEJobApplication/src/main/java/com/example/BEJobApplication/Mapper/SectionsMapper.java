package com.example.BEJobApplication.Mapper;

import com.example.BEJobApplication.DTO.SectionsDTO;
import com.example.BEJobApplication.Entity.Sections;

public class SectionsMapper {

    // Chuyển đổi từ Entity -> DTO
    public static SectionsDTO toSectionsDTO(Sections sections) {
        if (sections == null) {
            return null;
        }

        SectionsDTO sectionsDTO = new SectionsDTO();
        sectionsDTO.setId(sections.getId());
        sectionsDTO.setSectionsKey(sections.getSections_key());
        sectionsDTO.setSectionName(sections.getSection_name());

        return sectionsDTO;
    }

    // Chuyển đổi từ DTO -> Entity
    public static Sections toSectionsEntity(SectionsDTO sectionsDTO) {
        if (sectionsDTO == null) {
            return null;
        }

        Sections sections = new Sections();
        sections.setId(sectionsDTO.getId());
        sections.setSections_key(sectionsDTO.getSectionsKey());
        sections.setSection_name(sectionsDTO.getSectionName());

        return sections;
    }
}
