package com.example.PMS.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.PMS.DTO.ApplicationDTO.*;
import com.example.PMS.Entity.*;
import com.example.PMS.Repository.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationsService {

    private final ApplicationRepository applicationRepo;
    private final StudentRepository studentRepo;
    private final JobDrivesRepository driveRepo;
    private final ApplicationStatusRepository statusRepo;

    // =========================
    // STUDENT OPERATIONS
    // =========================

    @Transactional
    public ApplicationResponseDTO applyForDrive(Long driveId) {

        Student student = getLoggedInStudent();

        JobDrive drive = driveRepo.findById(driveId)
                .orElseThrow(() -> new RuntimeException("Drive not found"));

        // Prevent duplicate application
        if (applicationRepo.existsByStudentAndDrive(student, drive)) {
            throw new RuntimeException("Already applied for this drive");
        }

        // Create application
        Application application = new Application();
        application.setStudent(student);
        application.setDrive(drive);
        application.setAppliedAt(LocalDateTime.now());

        applicationRepo.save(application);

        // Initial status
        ApplicationStatus status = new ApplicationStatus();
        status.setApplication(application);
        status.setStatus("APPLIED");
        status.setUpdatedAt(LocalDateTime.now());

        statusRepo.save(status);

        return mapToResponseDTO(application, status.getStatus());
    }

    public List<ApplicationResponseDTO> getMyApplications() {
        Student student = getLoggedInStudent();

        return applicationRepo.findByStudent(student)
                .stream()
                .map(app -> {
                    String currentStatus = statusRepo.findTopByApplicationOrderByUpdatedAtDesc(app)
                            .map(ApplicationStatus::getStatus)
                            .orElse("APPLIED");

                    return mapToResponseDTO(app, currentStatus);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void withdrawApplication(Long applicationId) {
        Student student = getLoggedInStudent();

        Application app = applicationRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (!app.getStudent().getId().equals(student.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        String currentStatus = statusRepo.findTopByApplicationOrderByUpdatedAtDesc(app)
                .map(ApplicationStatus::getStatus)
                .orElse("");

        if (!currentStatus.equals("APPLIED")) {
            throw new RuntimeException("Cannot withdraw after shortlist");
        }

        applicationRepo.delete(app);
    }

    // =========================
    // ADMIN OPERATIONS
    // =========================

    public List<ApplicationResponseDTO> getApplicationsByDrive(Long driveId) {
        JobDrive drive = driveRepo.findById(driveId)
                .orElseThrow(() -> new RuntimeException("Drive not found"));

        return applicationRepo.findByDrive(drive)
                .stream()
                .map(app -> {
                    String currentStatus = statusRepo.findTopByApplicationOrderByUpdatedAtDesc(app)
                            .map(ApplicationStatus::getStatus)
                            .orElse("APPLIED");

                    return mapToResponseDTO(app, currentStatus);
                })
                .collect(Collectors.toList());
    }

    public ApplicationResponseDTO getApplicationById(Long applicationId) {
        Application app = applicationRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        String currentStatus = statusRepo.findTopByApplicationOrderByUpdatedAtDesc(app)
                .map(ApplicationStatus::getStatus)
                .orElse("APPLIED");

        return mapToResponseDTO(app, currentStatus);
    }

    @Transactional
    public void updateApplicationStatus(Long applicationId, ApplicationStatusUpdateDTO dto) {

        Application app = applicationRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        ApplicationStatus status = new ApplicationStatus();
        status.setApplication(app);
        status.setStatus(dto.getStatus());
        status.setRemarks(dto.getRemarks());
        status.setUpdatedAt(LocalDateTime.now());

        statusRepo.save(status);
    }

    public List<ApplicationStatusResponseDTO> getStatusHistory(Long applicationId) {

        Application app = applicationRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        return statusRepo.findByApplicationOrderByUpdatedAtAsc(app)
                .stream()
                .map(s -> {
                    ApplicationStatusResponseDTO dto = new ApplicationStatusResponseDTO();
                    dto.setStatus(s.getStatus());
                    dto.setUpdatedAt(s.getUpdatedAt());
                    dto.setRemarks(s.getRemarks());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // =========================
    // HELPER METHODS
    // =========================

    private ApplicationResponseDTO mapToResponseDTO(Application app, String status) {
        ApplicationResponseDTO dto = new ApplicationResponseDTO();
        dto.setApplicationId(app.getId());
        dto.setStudentName(app.getStudent().getName());
        dto.setCompanyName(app.getDrive().getCompany().getCompanyName());
        dto.setJobRole(app.getDrive().getJobRole());
        dto.setCurrentStatus(status);
        dto.setAppliedAt(app.getAppliedAt());
        return dto;
    }

    private Student getLoggedInStudent() {
        // Replace this with actual Spring Security logic
        // Example:
        // String email =
        // SecurityContextHolder.getContext().getAuthentication().getName();
        // return studentRepo.findByEmail(email).orElseThrow(...);

        return studentRepo.findById(1L).orElseThrow(); // TEMP for testing
    }
}
