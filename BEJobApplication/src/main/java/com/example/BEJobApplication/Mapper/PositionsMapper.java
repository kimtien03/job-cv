package com.example.BEJobApplication.Mapper;

import com.example.BEJobApplication.DTO.PositionsDTO;
import com.example.BEJobApplication.Entity.Positions;
import com.example.BEJobApplication.Entity.Industries;

public class PositionsMapper {

    // Chuyển từ Positions Entity sang PositionsDTO
    public static PositionsDTO toPositionsDTO(Positions position) {
        if (position == null) {
            return null;
        }

        return new PositionsDTO(
                position.getId(),
                position.getName(),
                position.getIndustry() != null ? position.getIndustry().getId() : null
        );
    }

    // Chuyển từ PositionsDTO sang Positions Entity
    public static Positions toPositionsEntity(PositionsDTO positionsDTO, Industries industry) {
        if (positionsDTO == null) {
            return null;
        }

        Positions position = new Positions();
        position.setId(positionsDTO.getId());
        position.setName(positionsDTO.getName());
        position.setIndustry(industry);  // Thiết lập Industry từ ID (thực hiện tìm kiếm trong Repository)
        return position;
    }
}
