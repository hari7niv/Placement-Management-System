package com.example.PMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PMS.Entity.ApplicationStatus;

public interface ApplicationStatusRepository extends JpaRepository<ApplicationStatus, Long> {

}
