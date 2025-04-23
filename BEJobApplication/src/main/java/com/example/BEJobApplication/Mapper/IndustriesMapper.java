package com.example.BEJobApplication.Mapper;

import com.example.BEJobApplication.DTO.IndustriesDTO;
import com.example.BEJobApplication.Entity.Industries;

public class IndustriesMapper {

    // Chuyển từ Entity -> DTO
    public static IndustriesDTO toIndustriesDTO(Industries industries) {
        if (industries == null) return null;
        return new IndustriesDTO(industries.getId(), industries.getName());
    }

    // Chuyển từ DTO -> Entity
    public static Industries toIndustriesEntity(IndustriesDTO industriesDTO) {
        if (industriesDTO == null) return null;
        return new Industries(industriesDTO.getId(), industriesDTO.getName());
    }
}
