package com.example.PMS.DTO;

import com.example.PMS.Enum.Status;

import lombok.Data;

@Data
public class ApplicationStatusDTO {
    private Long aid;
    private Status status;
    private String remarks;
}
