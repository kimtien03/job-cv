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

    public List<TemplateCvsDTO> getAllTemplateCvs() {
        List<Template_cvs> list = templateCvsRepository.findAll();
        return list.stream().map(TemplateCvsMapper::toDTO).toList();
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

    public List<TemplateCvsDTO> getTemplateByPositionAndStyle(Integer positionId, Integer styleId) {
        List<Template_cvs> templates;

        // Nếu cả hai cùng null → lấy tất cả
        if (positionId == null && styleId == null) {
            templates = templateCvsRepository.findAll();
        }
        // Nếu chỉ có positionId → lọc theo position
        else if (positionId != null && styleId == null) {
            templates = templateCvsRepository.findByPositionsId(positionId);
        }
        // Nếu chỉ có styleId → lọc theo style
        else if (positionId == null && styleId != null) {
            templates = templateCvsRepository.findByStyleId(styleId);
        }
        // Cả hai đều có → lọc theo cả hai
        else {
            templates = templateCvsRepository.findByPositionsIdAndStyleId(positionId, styleId);
        }

        if (templates.isEmpty()) {
            throw new NoFoundException("Không tìm thấy TemplateCV phù hợp với các điều kiện lọc.");
        }

        return templates.stream()
                .map(TemplateCvsMapper::toDTO)
                .collect(Collectors.toList());
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
