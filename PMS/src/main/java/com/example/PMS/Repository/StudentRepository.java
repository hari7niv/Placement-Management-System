package com.example.PMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PMS.Entity.Students;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Students, Long> {
    Optional<Students> findByEmail(String email);
}
