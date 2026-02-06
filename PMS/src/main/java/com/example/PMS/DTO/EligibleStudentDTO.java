package com.example.PMS.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EligibleStudentDTO {

    private Long studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String branch;
    private Double cgpa;
    private String companyName;
}