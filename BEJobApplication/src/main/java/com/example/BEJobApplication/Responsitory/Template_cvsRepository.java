package com.example.BEJobApplication.Responsitory;

import com.example.BEJobApplication.Entity.Template_cvs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Template_cvsRepository extends  JpaRepository<Template_cvs,Integer> {
    List<Template_cvs> findByPositionsIdAndStyleId(Integer positionId, Integer styleId);

    // Lọc theo Position (dựa trên thực thể Positions)
    List<Template_cvs> findByPositionsId(Integer positionId);

    // Lọc theo Style (dựa trên thực thể Styles)
    List<Template_cvs> findByStyleId(Integer styleId);
}
