package com.example.PMS.Repository;

import java.util.List;

import com.example.PMS.Entity.JobDrives;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDrivesRepository extends JpaRepository<JobDrives, Long> {
    List<JobDrives> findAllByActiveTrue();

}
 