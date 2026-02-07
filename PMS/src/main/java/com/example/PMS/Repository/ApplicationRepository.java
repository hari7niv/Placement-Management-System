package com.example.PMS.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PMS.Entity.Applications;

@Repository
public interface ApplicationRepository extends JpaRepository<Applications, Long> {
    List<Applications> findByDrive_Drive_id(Long driveId);
}
