package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.DTO.TemplateCvsDTO;
import com.example.BEJobApplication.Entity.Template_cvs;
import com.example.BEJobApplication.Entity.Positions;
import com.example.BEJobApplication.Entity.Styles;
import com.example.BEJobApplication.Mapper.TemplateCvsMapper;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Responsitory.PositionsRepository;
import com.example.BEJobApplication.Responsitory.StylesRepository;
import com.example.BEJobApplication.Responsitory.Template_cvsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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


    public TemplateCvsDTO getTemplateCvsById(Integer id) {
        Template_cvs templateCvs = templateCvsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TemplateCvs not found with ID " + id));
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

    public TemplateCvsDTO saveTemplateCvs(TemplateCvsDTO dto) {
        Positions position = positionsRepository.findById(dto.getPositionId())
                .orElseThrow(() -> new RuntimeException("Position not found with ID " + dto.getPositionId()));

        Styles style = stylesRepository.findById(dto.getStyleId())
                .orElseThrow(() -> new RuntimeException("Style not found with ID " + dto.getStyleId()));

        Template_cvs templateCvsEntity = TemplateCvsMapper.toEntity(dto, position, style);

        Template_cvs savedTemplateCvs = templateCvsRepository.save(templateCvsEntity);

        return TemplateCvsMapper.toDTO(savedTemplateCvs);
    }

    public TemplateCvsDTO updateTemplateCvs(Integer id, TemplateCvsDTO dto) {
        Template_cvs existing = templateCvsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy TemplateCV với ID: " + id));

        Positions position = positionsRepository.findById(dto.getPositionId())
                .orElseThrow(() -> new NoFoundException("Không tìm thấy Position với ID: " + dto.getPositionId()));

        Styles style = stylesRepository.findById(dto.getStyleId())
                .orElseThrow(() -> new NoFoundException("Không tìm thấy Style với ID: " + dto.getStyleId()));

        existing.setPositions(position);
        existing.setStyle(style);

        return TemplateCvsMapper.toDTO(templateCvsRepository.save(existing));
    }

    public void deleteTemplateCvs(Integer id) {
        if (!templateCvsRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy TemplateCV với ID: " + id);
        }
        templateCvsRepository.deleteById(id);
    }

    public TemplateCvsDTO createTemplateCvs(TemplateCvsDTO dto) {
        if (dto.getPositionId() == null || dto.getStyleId() == null) {
            throw new IllegalArgumentException("PositionId và StyleId không được để trống.");
        }

        // Kiểm tra tồn tại của Position và Style
        Positions position = positionsRepository.findById(dto.getPositionId())
                .orElseThrow(() -> new NoFoundException("Không tìm thấy Position với ID: " + dto.getPositionId()));

        Styles style = stylesRepository.findById(dto.getStyleId())
                .orElseThrow(() -> new NoFoundException("Không tìm thấy Style với ID: " + dto.getStyleId()));

        // Chuyển DTO -> Entity
        Template_cvs entity = TemplateCvsMapper.toEntity(dto, position, style);

        // Lưu và trả lại DTO
        Template_cvs saved = templateCvsRepository.save(entity);
        return TemplateCvsMapper.toDTO(saved);
    }


}
