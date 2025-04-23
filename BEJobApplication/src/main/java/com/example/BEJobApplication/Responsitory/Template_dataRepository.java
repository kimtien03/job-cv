package com.example.BEJobApplication.Responsitory;

import com.example.BEJobApplication.Entity.Template_data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Template_dataRepository extends JpaRepository<Template_data,Integer> {
}
