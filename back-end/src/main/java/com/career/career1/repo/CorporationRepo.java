package com.career.career1.repo;

import com.career.career1.model.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporationRepo extends JpaRepository<Corporation, Integer>{
    
}
