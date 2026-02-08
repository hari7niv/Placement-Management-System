package com.example.PMS.Repository;

import java.util.List;
import java.util.Optional;

import com.example.PMS.Entity.Companies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.PMS.Entity.JobDrives;

public interface CompaniesRepository extends JpaRepository<Companies,Long> {
    Optional<Companies> findByCompanyName(String name);

    @Query(value="""
            Select j.* from job_drives j join Company c on c.company_id = j.company_id where c.company_id = :companyId
            """,nativeQuery=true)
    List<JobDrives> viewCompanyDrives(@Param("companyId") Long companyId);
}
