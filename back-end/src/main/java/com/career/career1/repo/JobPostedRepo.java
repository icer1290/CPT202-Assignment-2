package com.career.career1.repo;

import com.career.career1.model.JobPosted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostedRepo extends JpaRepository<JobPosted, Integer>{
    
}
