package com.example.PMS.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class JobDrives {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driveId;

    private String job_role;
    private Integer package_lpa;
    private String eligible_branches;
    private Integer min_cgpa;
    private Integer max_backlogs;
    private LocalDate drive_date;
    private LocalDate application_deadline;
    @Column(nullable = false)
    private Boolean active=true;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Companies company;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin created_by;
}
 