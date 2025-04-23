package com.example.BEJobApplication.Responsitory;

import com.example.BEJobApplication.Entity.Styles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StylesRepository extends JpaRepository<Styles,Integer> {
}
