package com.example.PMS.Entity;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Companies {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long company_id;

    private String companyName;

    private String industry;

    private String website;

    private String contact_email;

    private String role;

    @CreationTimestamp
    private LocalDateTime created_at;

}

