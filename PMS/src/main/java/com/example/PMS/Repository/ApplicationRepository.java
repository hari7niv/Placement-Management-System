package com.example.PMS.Repository;

import com.example.PMS.Entity.Applications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Applications, Long> {

}