package com.example.PMS.DTO.ApplicationDTO;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ApplicationResponseDTO {

    private Long applicationId;
    private String studentName;
    private String companyName;
    private String jobRole;
    private String currentStatus;
    private LocalDateTime appliedAt;
}
