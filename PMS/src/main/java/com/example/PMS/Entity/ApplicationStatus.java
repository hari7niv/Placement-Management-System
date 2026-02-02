package com.example.PMS.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ApplicationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long status_id;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Applications application;

    private String status;

    private LocalDateTime updated_at;

    private String remarks;
}