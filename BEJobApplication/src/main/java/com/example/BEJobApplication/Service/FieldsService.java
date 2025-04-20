package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.DTO.FieldsDTO;
import com.example.BEJobApplication.Entity.Fields;
import com.example.BEJobApplication.Entity.Sections;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Mapper.FieldsMapper;
import com.example.BEJobApplication.Responsitory.FieldsRepository;
import com.example.BEJobApplication.Responsitory.SectionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldsService {

    @Autowired
    private FieldsRepository fieldsRepository;

    @Autowired
    private SectionsRepository sectionsRepository;

    // Tạo mới Field
    public FieldsDTO createField(FieldsDTO fieldsDTO) {
        // Kiểm tra nếu Section tồn tại
        Sections section = sectionsRepository.findById(fieldsDTO.getSectionId())
                .orElseThrow(() -> new NoFoundException("Không tìm thấy Section với ID: " + fieldsDTO.getSectionId()));

        // Chuyển từ DTO sang Entity
        Fields field = FieldsMapper.toFieldsEntity(fieldsDTO, section);

        // Lưu vào cơ sở dữ liệu
        field = fieldsRepository.save(field);

        // Trả về DTO của đối tượng đã lưu
        return FieldsMapper.toFieldsDTO(field);
    }

    // Lấy thông tin Field theo ID
    public FieldsDTO getFieldById(Integer id) {
        Fields field = fieldsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy Field với ID: " + id));

        // Trả về DTO của Field
        return FieldsMapper.toFieldsDTO(field);
    }

    // Cập nhật thông tin Field
    public FieldsDTO updateField(Integer id, FieldsDTO fieldsDTO) {
        // Kiểm tra Field có tồn tại hay không
        Fields existingField = fieldsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy Field với ID: " + id));

        // Kiểm tra Section
        Sections section = sectionsRepository.findById(fieldsDTO.getSectionId())
                .orElseThrow(() -> new NoFoundException("Không tìm thấy Section với ID: " + fieldsDTO.getSectionId()));

        // Cập nhật các giá trị từ DTO vào Entity
        existingField.setField_name(fieldsDTO.getFieldName());
        existingField.setField_type(fieldsDTO.getFieldType());
        existingField.setSection(section); // Cập nhật Section

        // Lưu và trả về DTO sau khi cập nhật
        Fields updatedField = fieldsRepository.save(existingField);
        return FieldsMapper.toFieldsDTO(updatedField);
    }

    // Xóa Field theo ID
    public void deleteField(Integer id) {
        Fields field = fieldsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy Field với ID: " + id));

        // Xóa Field
        fieldsRepository.delete(field);
    }
}
