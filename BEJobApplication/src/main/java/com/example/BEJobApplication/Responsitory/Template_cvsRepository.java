package com.example.BEJobApplication.Responsitory;

import com.example.BEJobApplication.Entity.Template_cvs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Template_cvsRepository extends  JpaRepository<Template_cvs,Integer> {
    Page<Template_cvs> findByPositions_IdAndStyle_Id(Integer positionId, Integer styleId, Pageable pageable);
    Page<Template_cvs> findByPositions_Id(Integer positionId, Pageable pageable);
    Page<Template_cvs> findByStyle_Id(Integer styleId, Pageable pageable);
    Page<Template_cvs> findByPositions_Industry_Id(Integer industryId, Pageable pageable);
    Page<Template_cvs> findByPositions_Industry_IdAndStyle_Id(Integer industryId, Integer styleId, Pageable pageable);
}
