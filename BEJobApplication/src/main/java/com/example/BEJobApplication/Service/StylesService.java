package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.DTO.StylesDTO;
import com.example.BEJobApplication.Entity.Styles;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Mapper.StylesMapper;
import com.example.BEJobApplication.Responsitory.StylesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StylesService {

    @Autowired
    private com.example.BEJobApplication.Responsitory.StylesRepository stylesRepository;

    // Lấy tất cả các styles
    public List<StylesDTO> getAllStyles() {
        List<Styles> styles = stylesRepository.findAll();
        return styles.stream()
                .map(StylesMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Lấy style theo ID
    public StylesDTO getStyleById(Integer id) {
        Styles style = stylesRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy Style với ID: " + id));
        return StylesMapper.toDTO(style);
    }

    // Thêm mới một Style
    public StylesDTO createStyle(StylesDTO stylesDTO) {
        Styles style = StylesMapper.toEntity(stylesDTO);
        style = stylesRepository.save(style);
        return StylesMapper.toDTO(style);
    }

    // Cập nhật thông tin một Style
    public StylesDTO updateStyle(Integer id, StylesDTO stylesDTO) {
        Styles existingStyle = stylesRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy Style với ID: " + id));

        // Cập nhật các trường
        existingStyle.setStyle_name(stylesDTO.getStyleName());
        existingStyle.setFile_name(stylesDTO.getFileName());
        existingStyle.setCss_name(stylesDTO.getCssName());

        existingStyle = stylesRepository.save(existingStyle);
        return StylesMapper.toDTO(existingStyle);
    }

    // Xóa Style theo ID
    public void deleteStyle(Integer id) {
        Styles style = stylesRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy Style với ID: " + id));
        stylesRepository.delete(style);
    }
}
