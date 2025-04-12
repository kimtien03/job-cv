package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.Entity.Skill;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Responsitory.SkillReponsitory;
import com.example.BEJobApplication.Responsitory.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillReponsitory skillRepository;

    @Autowired
    private UserReponsitory userRepository;

    public List<Skill> getAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        if (skills.isEmpty()) {
            throw new NoFoundException("Không có kỹ năng nào trong hệ thống.");
        }
        return skills;
    }

    public Skill getSkillById(Integer id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy kỹ năng với ID: " + id));
    }

    public Skill createSkill(Skill skill) {
        validateSkill(skill);
        return skillRepository.save(skill);
    }

    public Skill updateSkill(Integer id, Skill updatedSkill) {
        if (updatedSkill.getId() != null && !updatedSkill.getId().equals(id)) {
            throw new NoFoundException("ID không thể thay đổi.");
        }

        validateSkill(updatedSkill);

        return skillRepository.save(updatedSkill);
    }

    public Boolean deleteSkill(Integer id) {
        if (!skillRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy kỹ năng với ID: " + id);
        }
        skillRepository.deleteById(id);
        return true;
    }

    private void validateSkill(Skill skill) {
        if (skill.getName() == null || skill.getName().trim().isEmpty()) {
            throw new NoFoundException("Tên kỹ năng không được để trống.");
        }

        if (skill.getUser() == null || !userRepository.existsById(skill.getUser().getId())) {
            throw new NoFoundException("Người dùng không tồn tại hoặc không hợp lệ.");
        }
    }
}
