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

    // =========================
    // STUDENT OPERATIONS
    // =========================

    // Apply for a job drive
    @PostMapping("/apply/{driveId}")
    public ResponseEntity<ApplicationResponseDTO> applyForDrive(
            @PathVariable Long driveId) {

        ApplicationResponseDTO response = applicationService.applyForDrive(driveId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // View logged-in student's applications
    @GetMapping("/my")
    public ResponseEntity<List<ApplicationResponseDTO>> getMyApplications() {

        List<ApplicationResponseDTO> applications = applicationService.getMyApplications();

        return ResponseEntity.ok(applications);
    }

    // Withdraw application (only if not shortlisted)
    @DeleteMapping("/{applicationId}")
    public ResponseEntity<String> withdrawApplication(
            @PathVariable Long applicationId) {

        applicationService.withdrawApplication(applicationId);

        return ResponseEntity.ok("Application withdrawn successfully");
    }

    // =========================
    // ADMIN OPERATIONS
    // =========================

    // View all applications for a job drive
    @GetMapping("/drive/{driveId}")
    public ResponseEntity<List<ApplicationResponseDTO>> getApplicationsByDrive(
            @PathVariable Long driveId) {

        List<ApplicationResponseDTO> applications = applicationService.getApplicationsByDrive(driveId);

        return ResponseEntity.ok(applications);
    }

    // View application by ID
    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationResponseDTO> getApplicationById(
            @PathVariable Long applicationId) {

        ApplicationResponseDTO response = applicationService.getApplicationById(applicationId);

        return ResponseEntity.ok(response);
    }

    // Update application status (SHORTLISTED, REJECTED, etc.)
    @PutMapping("/{applicationId}/status")
    public ResponseEntity<String> updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestBody ApplicationStatusUpdateDTO request) {

        applicationService.updateApplicationStatus(applicationId, request);

        return ResponseEntity.ok("Application status updated successfully");
    }

    // View full status history of an application
    @GetMapping("/{applicationId}/status-history")
    public ResponseEntity<List<ApplicationStatusResponseDTO>> getStatusHistory(
            @PathVariable Long applicationId) {

        List<ApplicationStatusResponseDTO> history = applicationService.getStatusHistory(applicationId);

        return ResponseEntity.ok(history);
    }
}
