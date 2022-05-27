package com.career.career1.repo;

import com.career.career1.model.ResumeReceived;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeReceivedRepo extends JpaRepository<ResumeReceived, Integer>{
    
}
