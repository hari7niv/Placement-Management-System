package com.example.PMS.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Applications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long application_id;

    private Long student_id;

    @ManyToOne
    @JoinColumn(name = "drive_id", nullable = false)
    private JobDrives drive;

    @CreationTimestamp
    private LocalDateTime applied_at;
}

