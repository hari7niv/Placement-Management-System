package com.example.PMS.DTO.ApplicationDTO;

import lombok.Data;

@Data
public class ApplicationStatusUpdateDTO {
    private String status; // SHORTLISTED, REJECTED, OFFERED
    private String remarks;
}
