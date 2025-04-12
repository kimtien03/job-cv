package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.Entity.Education;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Responsitory.EducationReponsitory;
import com.example.BEJobApplication.Responsitory.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EducationService {
    @Autowired
    private EducationReponsitory educationReponsitory;
    @Autowired
    private UserReponsitory userRepository;
    public List<Education> getAllEducation(){
        List<Education> educations= educationReponsitory.findAll();
        if (educations.isEmpty()) {
            throw new NoFoundException("Không có Education nào trong hệ thống");
        }
        return educations;
    }
    public Education getEducationById(Integer id){
        return educationReponsitory.findById(id)
                .orElseThrow(()->new NoFoundException("Không tìm thấy Education có id ="+ id));

    }
    public Boolean deleteEducation(Integer id){
        if(!educationReponsitory.existsById(id)){
            throw new NoFoundException("Không tìm thấy Education với ID: " + id);
        }
        educationReponsitory.deleteById(id);
        return true;
    }

    public Education createEducation(Education education) {
        validateEducation(education);
        return educationReponsitory.save(education);
    }

    public Education updateEducation(Integer id, Education updatedEducation) {
        if (updatedEducation.getId() != null && !updatedEducation.getId().equals(id)) {
            throw new NoFoundException("ID không thể thay đổi");
        }

        validateEducation(updatedEducation);

        return educationReponsitory.save(updatedEducation);
    }

    private void validateEducation(Education education) {
        if (education.getUser() == null || education.getUser().getId() == null) {
            throw new NoFoundException("Người dùng không được để trống.");
        }

        if (!userRepository.existsById(education.getUser().getId())) {
            throw new NoFoundException("Người dùng với ID " + education.getUser().getId() + " không tồn tại.");
        }

        if (education.getSchool() == null || education.getSchool().trim().isEmpty()) {
            throw new NoFoundException("Trường học không được để trống.");
        }

        if (education.getStartDate() == null) {
            throw new NoFoundException("Ngày bắt đầu không được để trống.");
        }

        if (education.getEndDate() != null && education.getEndDate().isBefore(education.getStartDate())) {
            throw new NoFoundException("Ngày kết thúc không thể trước ngày bắt đầu.");
        }
    }
}