package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.Entity.Template_cvs;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Responsitory.Template_cvsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Template_cvsService {

    @Autowired
    private Template_cvsRepository templateCvsRepository;

    // Lấy tất cả template_cvs
    public List<Template_cvs> getAllTemplateCvs() {
        List<Template_cvs> list = templateCvsRepository.findAll();
        if (list.isEmpty()) {
            throw new NoFoundException("Không có dữ liệu template CV nào.");
        }
        return list;
    }

    // Lấy template_cvs theo ID
    public Template_cvs getTemplateCvsById(Integer id) {
        return templateCvsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy template CV với id: " + id));
    }

    // Tạo mới template_cvs
    public Template_cvs createTemplateCvs(Template_cvs templateCvs) {
        return templateCvsRepository.save(templateCvs);
    }

    // Cập nhật template_cvs
    public Template_cvs updateTemplateCvs(Integer id, Template_cvs details) {
        Template_cvs existing = templateCvsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy template CV với id: " + id));

        existing.setSection_id(details.getSection_id());
        existing.setStyle_id(details.getStyle_id());

        return templateCvsRepository.save(existing);
    }

    // Xoá template_cvs
    public void deleteTemplateCvs(Integer id) {
        if (!templateCvsRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy template CV với id: " + id);
        }
        templateCvsRepository.deleteById(id);
    }
    public List<Template_cvs> findByPositionIdAndStyleId (Integer position_id,Integer style_id)
    {
        List<Template_cvs> list = templateCvsRepository.findByPositionIdAndStyleId(position_id,style_id);
        if (list.isEmpty()) {
            throw new NoFoundException("Không có dữ liệu template CV nào.");
        }
        return list;
    }
}
