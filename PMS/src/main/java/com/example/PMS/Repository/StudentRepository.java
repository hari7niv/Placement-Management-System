package com.example.PMS.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.PMS.DTO.EligibleStudentDTO;
import com.example.PMS.Entity.Companies;
import com.example.PMS.Entity.Students;

public interface StudentRepository extends JpaRepository<Students, Long> {
    Optional<Students> findByEmail(String email);
    @Query(value = "Select * from Students where is_verified=false;", nativeQuery = true)
    List<Students> getUnVerifiedStudents();

    @Query(value = """
                   Select DISTINCT s.student_id,s.first_name,s.last_name,s.email,s.branch,s.cgpa,
                         c.company_name from Students s join  job_drives j on 
                         s.cgpa>=j.min_cgpa and s.backlog_count <= j.max_backlogs and 
                         FIND_IN_SET(LOWER(s.branch), LOWER(j.eligible_branches)) JOIN companies c ON j.company_id = c.company_id
                         """ ,nativeQuery = true)
    List<EligibleStudentDTO> getEligibleStudents();

    @Query(value = """
            Select Distinct c.*  from Company c join job_drives j on j.company_id = c.company_id
            join Students s on s.student_id = :studentId where s.cgpa>=j.min_cgpa and s.backlog_count <= j.max_backlogs and 
                         FIND_IN_SET(LOWER(s.branch), LOWER(j.eligible_branches))
            """,nativeQuery=true)
    List<Companies> getEligibleCompanies(@Param("studentId") Long studentId);

}
