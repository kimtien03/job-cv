package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.Entity.Styles;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Responsitory.StylesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StylesService {

    @Autowired
    private StylesRepository stylesRepository;

    // Lấy tất cả style
    public List<Styles> getAllStyles() {
        List<Styles> stylesList = stylesRepository.findAll();
        if (stylesList.isEmpty()) {
            throw new NoFoundException("Không có style nào trong hệ thống.");
        }
        return stylesList;
    }

    // Lấy style theo ID
    public Styles getStyleById(Integer id) {
        return stylesRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy style với id: " + id));
    }

    // Tạo mới style
    public Styles createStyle(Styles style) {
        return stylesRepository.save(style);
    }

    // Cập nhật style
    public Styles updateStyle(Integer id, Styles updatedStyle) {
        Styles existingStyle = stylesRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy style với id: " + id));

        existingStyle.setStyle_name(updatedStyle.getStyle_name());
        existingStyle.setFile_name(updatedStyle.getFile_name());
        existingStyle.setCss_name(updatedStyle.getCss_name());

        return stylesRepository.save(existingStyle);
    }

    // Xóa style
    public void deleteStyle(Integer id) {
        if (!stylesRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy style với id: " + id);
        }
        stylesRepository.deleteById(id);
    }
}
