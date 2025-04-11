package com.example.bejobapplication.Service;

import com.example.bejobapplication.Entity.Experience;
import com.example.bejobapplication.Exception.NoFoundException;
import com.example.bejobapplication.Reponsitory.ExperienceReponsitory;
import com.example.bejobapplication.Reponsitory.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceReponsitory experienceRepository;

    @Autowired
    private UserReponsitory userRepository;

    public List<Experience> getAllExperience() {
        List<Experience> experiences = experienceRepository.findAll();
        if (experiences.isEmpty()) {
            throw new NoFoundException("Không có kinh nghiệm làm việc nào trong hệ thống.");
        }
        return experiences;
    }

    public Experience getExperienceById(Integer id) {
        return experienceRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy kinh nghiệm làm việc với ID: " + id));
    }

    public Boolean deleteExperience(Integer id) {
        if (!experienceRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy kinh nghiệm làm việc với ID: " + id);
        }
        experienceRepository.deleteById(id);
        return true;
    }

    public Experience createExperience(Experience experience) {
        validateExperience(experience);
        return experienceRepository.save(experience);
    }

    public Experience updateExperience(Integer id, Experience updatedExperience) {
        if (updatedExperience.getId() != null && !updatedExperience.getId().equals(id)) {
            throw new IllegalArgumentException("ID không thể thay đổi");
        }

        validateExperience(updatedExperience);
        updatedExperience.setId(id);
        return experienceRepository.save(updatedExperience);
    }

    private void validateExperience(Experience experience) {
        if (experience.getUser() == null || experience.getUser().getId() == null) {
            throw new NoFoundException("Người dùng không được để trống.");
        }

        if (!userRepository.existsById(experience.getUser().getId())) {
            throw new NoFoundException("Người dùng với ID " + experience.getUser().getId() + " không tồn tại.");
        }

        if (experience.getCompany() == null || experience.getCompany().trim().isEmpty()) {
            throw new NoFoundException("Tên công ty không được để trống.");
        }

        if (experience.getPosition() == null || experience.getPosition().trim().isEmpty()) {
            throw new NoFoundException("Vị trí làm việc không được để trống.");
        }

        if (experience.getStartDate() == null) {
            throw new NoFoundException("Ngày bắt đầu không được để trống.");
        }

        if (experience.getEndDate() != null && experience.getEndDate().isBefore(experience.getStartDate())) {
            throw new NoFoundException("Ngày kết thúc không thể trước ngày bắt đầu.");
        }
    }
}
