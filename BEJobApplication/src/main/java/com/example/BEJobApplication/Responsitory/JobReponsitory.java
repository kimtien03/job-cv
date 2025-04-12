package com.example.BEJobApplication.Responsitory;

import com.example.BEJobApplication.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobReponsitory extends JpaRepository<Job,Integer> {
}
