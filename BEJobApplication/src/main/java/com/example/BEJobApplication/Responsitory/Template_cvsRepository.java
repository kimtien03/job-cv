package com.example.BEJobApplication.Responsitory;

import com.example.BEJobApplication.Entity.Template_cvs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Template_cvsRepository extends  JpaRepository<Template_cvs,Integer> {
//    List<Template_cvs> findByPositionIdAndStyleId(Integer styl_id,Integer position_id);
//    List<Template_cvs> findByPositionId(Integer position_id);
//    List<Template_cvs> findByStyleId(Integer styl_id);
}
