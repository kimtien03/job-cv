package com.example.BEJobApplication.Responsitory;

import com.example.BEJobApplication.Entity.Sections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionsRepository extends JpaRepository<Sections,Integer> {
}
