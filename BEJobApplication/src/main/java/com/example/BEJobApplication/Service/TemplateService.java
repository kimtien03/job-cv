package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.Entity.Template;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Responsitory.JobReponsitory;
import com.example.BEJobApplication.Responsitory.TemplateReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {

    @Autowired
    private TemplateReponsitory templateRepository;

    @Autowired
    private JobReponsitory jobRepository;

    public List<Template> getAllTemplates() {
        List<Template> templates = templateRepository.findAll();
        if (templates.isEmpty()) {
            throw new NoFoundException("Không có mẫu CV nào trong hệ thống.");
        }
        return templates;
    }

    public Template getTemplateById(Integer id) {
        return templateRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy mẫu CV với ID: " + id));
    }

    public Template createTemplate(Template template) {
        validateTemplate(template);
        return templateRepository.save(template);
    }

    public Template updateTemplate(Integer id, Template updatedTemplate) {
        if (updatedTemplate.getId() != null && !updatedTemplate.getId().equals(id)) {
            throw new NoFoundException("ID không thể thay đổi.");
        }

        validateTemplate(updatedTemplate);

        return templateRepository.save(updatedTemplate);
    }

    public Boolean deleteTemplate(Integer id) {
        if (!templateRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy mẫu CV với ID: " + id);
        }
        templateRepository.deleteById(id);
        return true;
    }

    private void validateTemplate(Template template) {
        if (template.getJob() == null || !jobRepository.existsById(template.getJob().getId())) {
            throw new NoFoundException("Công việc liên kết không tồn tại hoặc không hợp lệ.");
        }

        if (template.getContent() == null || template.getContent().trim().isEmpty()) {
            throw new NoFoundException("Nội dung template không được để trống.");
        }
    }
}
