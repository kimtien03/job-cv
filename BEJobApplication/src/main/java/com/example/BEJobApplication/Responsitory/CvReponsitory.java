package com.example.BEJobApplication.Responsitory;

import com.example.BEJobApplication.Entity.Cv;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CvReponsitory extends JpaRepository<Cv,Integer> {
}
