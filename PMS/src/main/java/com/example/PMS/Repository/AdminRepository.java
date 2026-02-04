package com.example.PMS.Repository;

import com.example.PMS.Entity.Admin;
import com.example.PMS.Entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByEmail(String email);
}
