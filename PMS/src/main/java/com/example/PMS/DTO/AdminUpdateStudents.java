package com.example.PMS.DTO;

import lombok.Data;

@Data
public class AdminUpdateStudents {
    private String branch;
    private Double cgpa;
    private Integer backlog_count;
}
