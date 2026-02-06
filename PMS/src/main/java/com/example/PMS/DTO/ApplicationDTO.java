package com.example.PMS.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationDTO {
    private Long aid;
    private Long sid;
    private LocalDateTime createTime;
}