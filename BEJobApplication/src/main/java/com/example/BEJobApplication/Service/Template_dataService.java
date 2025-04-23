package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.DTO.TemplateDataDTO;
import com.example.BEJobApplication.Entity.*;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Mapper.TemplateDataMapper;
import com.example.BEJobApplication.Responsitory.FieldsRepository;
import com.example.BEJobApplication.Responsitory.SectionsRepository;
import com.example.BEJobApplication.Responsitory.Template_cvsRepository;
import com.example.BEJobApplication.Responsitory.Template_dataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Template_dataService {

    @Autowired
    private Template_dataRepository templateDataRepository;

    @Autowired
    private Template_cvsRepository templateCvsRepository;

    @Autowired
    private SectionsRepository sectionsRepository;

    @Autowired
    private FieldsRepository fieldsRepository;

    // Lấy tất cả TemplateData
    public List<TemplateDataDTO> getAllTemplateData() {
        List<Template_data> dataList = templateDataRepository.findAll();
        return dataList.stream()
                .map(TemplateDataMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Lấy theo ID
    public TemplateDataDTO getById(Integer id) {
        Template_data templateData = templateDataRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy TemplateData với ID: " + id));
        return TemplateDataMapper.toDTO(templateData);
    }

    // Tạo mới TemplateData
    public TemplateDataDTO createTemplateData(TemplateDataDTO dto) {
        Template_data entity = new Template_data();

        Template_cvs template = templateCvsRepository.findById(dto.getTemplateCvId())
                .orElseThrow(() -> new NoFoundException("Không tìm thấy TemplateCV với ID: " + dto.getTemplateCvId()));

        Sections section = sectionsRepository.findById(dto.getSectionId())
                .orElseThrow(() -> new NoFoundException("Không tìm thấy Section với ID: " + dto.getSectionId()));

        Fields field = fieldsRepository.findById(dto.getFieldId())
                .orElseThrow(() -> new NoFoundException("Không tìm thấy Field với ID: " + dto.getFieldId()));

        entity.setTemplateCvs(template);
        entity.setSection(section);
        entity.setField(field);
        entity.setValue(dto.getValue());
        entity.setGroupIndex(dto.getGroupIndex());

        Template_data saved = templateDataRepository.save(entity);
        return TemplateDataMapper.toDTO(saved);
    }

    // Cập nhật
    public TemplateDataDTO updateTemplateData(Integer id, TemplateDataDTO dto) {
        Template_data existing = templateDataRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy TemplateData với ID: " + id));

        Template_cvs template = templateCvsRepository.findById(dto.getTemplateCvId())
                .orElseThrow(() -> new NoFoundException("Không tìm thấy TemplateCV với ID: " + dto.getTemplateCvId()));

        Sections section = sectionsRepository.findById(dto.getSectionId())
                .orElseThrow(() -> new NoFoundException("Không tìm thấy Section với ID: " + dto.getSectionId()));

        Fields field = fieldsRepository.findById(dto.getFieldId())
                .orElseThrow(() -> new NoFoundException("Không tìm thấy Field với ID: " + dto.getFieldId()));

        existing.setTemplateCvs(template);
        existing.setSection(section);
        existing.setField(field);
        existing.setValue(dto.getValue());
        existing.setGroupIndex(dto.getGroupIndex());

        Template_data updated = templateDataRepository.save(existing);
        return TemplateDataMapper.toDTO(updated);
    }

    // Xoá
    public void deleteTemplateData(Integer id) {
        if (!templateDataRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy TemplateData để xoá với ID: " + id);
        }
        templateDataRepository.deleteById(id);
    }
}
