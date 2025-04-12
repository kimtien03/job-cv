package com.example.BEJobApplication.Responsitory;

import com.example.BEJobApplication.Entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillReponsitory extends JpaRepository<Skill,Integer> {
}
