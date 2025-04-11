package com.example.bejobapplication.Reponsitory;

import com.example.bejobapplication.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobReponsitory extends JpaRepository<Job,Integer> {
}
