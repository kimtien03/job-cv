package com.example.BEJobApplication.Responsitory;

import com.example.BEJobApplication.DTO.PositionsDTO;
import com.example.BEJobApplication.Entity.Positions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionsRepository extends JpaRepository<Positions,Integer> {
    List<Positions> findByIndustryId(Integer industryId);
}
