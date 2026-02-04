package com.example.PMS.DTO.ApplicationDTO;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ApplicationStatusResponseDTO {
    private String status;
    private LocalDateTime updatedAt;
    private String remarks;
}

