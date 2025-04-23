package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.Entity.Template_data;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Responsitory.Template_dataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Template_dataService {

    @Autowired
    private Template_dataRepository templateDataRepository;

    // Lấy tất cả dữ liệu
    public List<Template_data> getAllTemplateData() {
        List<Template_data> dataList = templateDataRepository.findAll();
        if (dataList.isEmpty()) {
            throw new NoFoundException("Không có dữ liệu template nào.");
        }
        return dataList;
    }

    // Lấy theo ID
    public Template_data getTemplateDataById(Integer id) {
        return templateDataRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy dữ liệu template với id: " + id));
    }

    // Tạo mới
    public Template_data createTemplateData(Template_data templateData) {
        return templateDataRepository.save(templateData);
    }

    // Cập nhật
    public Template_data updateTemplateData(Integer id, Template_data templateDataDetails) {
        Template_data existingData = templateDataRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy dữ liệu template với id: " + id));

        existingData.setTemplate_cv_id(templateDataDetails.getTemplate_cv_id());
        existingData.setSection_id(templateDataDetails.getSection_id());
        existingData.setField_id(templateDataDetails.getField_id());
        existingData.setValue(templateDataDetails.getValue());
        existingData.setGroup_index(templateDataDetails.getGroup_index());

        return templateDataRepository.save(existingData);
    }

    // Xóa
    public void deleteTemplateData(Integer id) {
        if (!templateDataRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy dữ liệu template với id: " + id);
        }
        templateDataRepository.deleteById(id);
    }
}
