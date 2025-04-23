package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.DTO.TemplateCvsDTO;
import com.example.BEJobApplication.Entity.Template_cvs;
import com.example.BEJobApplication.Entity.Positions;
import com.example.BEJobApplication.Entity.Styles;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Mapper.PositionsMapper;
import com.example.BEJobApplication.Mapper.TemplateCvsMapper;

import com.example.BEJobApplication.Responsitory.PositionsRepository;
import com.example.BEJobApplication.Responsitory.StylesRepository;
import com.example.BEJobApplication.Responsitory.Template_cvsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Template_cvsService {
    @Autowired
    private Template_cvsRepository templateCvsRepository;

    @Autowired
    private PositionsRepository positionsRepository;

    @Autowired
    private StylesRepository stylesRepository;

    public TemplateCvsDTO saveTemplateCvs(TemplateCvsDTO dto) {
        // Tìm Position và Style theo ID từ DTO
        Positions position = positionsRepository.findById(dto.getPositionId())
                .orElseThrow(() -> new RuntimeException("Position not found with ID " + dto.getPositionId()));

        Styles style = stylesRepository.findById(dto.getStyleId())
                .orElseThrow(() -> new RuntimeException("Style not found with ID " + dto.getStyleId()));

        // Chuyển đổi từ DTO thành Entity
        Template_cvs templateCvsEntity = TemplateCvsMapper.toEntity(dto, position, style);

        // Lưu entity vào cơ sở dữ liệu
        Template_cvs savedTemplateCvs = templateCvsRepository.save(templateCvsEntity);

        // Chuyển entity đã lưu thành DTO và trả về
        return TemplateCvsMapper.toDTO(savedTemplateCvs);
    }

    public TemplateCvsDTO getTemplateCvsById(Integer id) {
        // Tìm Template_cvs theo ID
        Template_cvs templateCvs = templateCvsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TemplateCvs not found with ID " + id));

        // Chuyển đổi entity thành DTO
        return TemplateCvsMapper.toDTO(templateCvs);
    }

    public Page<TemplateCvsDTO> filterTemplates(Integer industryId, Integer positionId, Integer styleId, Pageable pageable) {
        Page<Template_cvs> entities;
        if (positionId != null && styleId != null) {
            entities = templateCvsRepository.findByPositions_IdAndStyle_Id(positionId, styleId, pageable);
        } else if (positionId != null) {
            entities = templateCvsRepository.findByPositions_Id(positionId, pageable);
        } else if (industryId != null && styleId != null) {
            entities = templateCvsRepository.findByPositions_Industry_IdAndStyle_Id(industryId, styleId, pageable);
        } else if (industryId != null) {
            entities = templateCvsRepository.findByPositions_Industry_Id(industryId, pageable);
        } else if (styleId != null) {
            entities = templateCvsRepository.findByStyle_Id(styleId, pageable);
        } else {
            entities = templateCvsRepository.findAll(pageable);
        }
        return entities.map(TemplateCvsMapper::toDTO);
    }

}
