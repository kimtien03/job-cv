package com.example.BEJobApplication.Responsitory;

import com.example.BEJobApplication.Entity.User_cvs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_cvsRepository extends JpaRepository<User_cvs, Integer>{
}
