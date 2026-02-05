package com.example.PMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PMS.Entity.Students;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Students, Long> {
    Optional<Students> findByEmail(String email);
    @Query(value = "Select * from Students where is_verified=true;", nativeQuery = true)
    List<Students> getVerifiedStudents();
}
