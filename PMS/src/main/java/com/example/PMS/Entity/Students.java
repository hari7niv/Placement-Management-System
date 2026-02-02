package com.example.PMS.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id ;

    @Column(unique=true, nullable=false)
    private String reference_number;

    private String first_name;
    private String last_name;

    @Column(unique=true, nullable=false)
    private String email;

    private String password_hash;

    @Column(unique=true, nullable=false)
    private String phone_number;

    private String branch;
    private Integer Year;

    private Double cgpa;

    private String backlog_count;
    private String resume_link;

    private Boolean is_verified;

    @CreationTimestamp
    private LocalDateTime created_at;


}
