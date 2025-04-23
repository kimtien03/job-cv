package com.example.BEJobApplication.Responsitory;

import com.example.BEJobApplication.Entity.Industries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustriesRepository  extends JpaRepository<Industries,Integer> {
}
