package com.example.PMS.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PMS.DTO.ApplicationDTO.ApplicationResponseDTO;
import com.example.PMS.DTO.ApplicationDTO.ApplicationStatusResponseDTO;
import com.example.PMS.DTO.ApplicationDTO.ApplicationStatusUpdateDTO;
import com.example.PMS.Service.ApplicationsService;

@RestController
@RequestMapping("/api/applications")
public class ApplicationsController {

    private final ApplicationsService applicationService;

    public ApplicationsController(ApplicationsService applicationService) {
        this.applicationService = applicationService;
    }

}
