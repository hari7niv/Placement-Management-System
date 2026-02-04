package com.example.PMS.DTO;
import lombok.Data;

@Data
public class UpdateProfileRequest {
    String branch;
    String first_name;
    String last_name;
    Double cgpa;
    String resume_link;
    String phone_number;
}
