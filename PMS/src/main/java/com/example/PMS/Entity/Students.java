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

    private Integer backlog_count;// change panniruka da error varum apparam aluvada poi inda table a delete pannu ok va
    private String resume_link;
    @Column(nullable=false)
    private Boolean is_verified=false;

    @CreationTimestamp
    private LocalDateTime created_at;

}


// {
//   "reference_number": "24017937",
//   "first_name": "karthik",
//   "last_name": "E",
//   "email": "karthil@gmail.com",
//   "password_hash": "1234",
//   "phone_number": "9828944085",
//   "branch": "CSE",
//   "Year": 2,
//   "cgpa": 8.0,
//   "is_verified": true,
//   "resume_link":"",
//   "backlog_count":0
// }
