package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.DTO.TemplateCvsDTO;
import com.example.BEJobApplication.Entity.Template_cvs;
import com.example.BEJobApplication.Entity.Positions;
import com.example.BEJobApplication.Entity.Styles;
import com.example.BEJobApplication.Mapper.TemplateCvsMapper;

import com.example.BEJobApplication.Responsitory.PositionsRepository;
import com.example.BEJobApplication.Responsitory.StylesRepository;
import com.example.BEJobApplication.Responsitory.Template_cvsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
