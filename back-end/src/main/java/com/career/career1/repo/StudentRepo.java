package com.career.career1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.career.career1.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    
}
