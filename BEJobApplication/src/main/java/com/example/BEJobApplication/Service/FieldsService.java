package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.Entity.Fields;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Responsitory.FieldsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldsService {

    @Autowired
    private FieldsRepository fieldsRepository;

    // Lấy tất cả các trường (fields)
    public List<Fields> getAllFields() {
        List<Fields> fieldsList = fieldsRepository.findAll();
        if (fieldsList.isEmpty()) {
            throw new NoFoundException("Không có dữ liệu các trường.");
        }
        return fieldsList;
    }

    // Lấy thông tin trường theo ID
    public Fields getFieldById(Integer id) {
        return fieldsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy trường với ID: " + id));
    }

    // Tạo mới trường
    public Fields createField(Fields field) {
        return fieldsRepository.save(field);
    }

    // Cập nhật thông tin trường
    public Fields updateField(Integer id, Fields updatedField) {
        Fields existingField = fieldsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy trường với ID: " + id));

        existingField.setSection_id(updatedField.getSection_id());
        existingField.setField_name(updatedField.getField_name());
        existingField.setField_type(updatedField.getField_type());

        return fieldsRepository.save(existingField);
    }

    // Xóa trường
    public void deleteIndustry(Integer id) {
        if (!fieldsRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy ngành với ID: " + id);
        }
        fieldsRepository.deleteById(id);
    }
}
