package com.example.PMS.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PMS.DTO.EligibleStudentDTO;
import com.example.PMS.DTO.UpdateDrives;
import com.example.PMS.Entity.JobDrives;
import com.example.PMS.Entity.Students;
import com.example.PMS.Service.JobDrivesService;

@RestController
@RequestMapping("/drives")
public class JobDrivesController {

    final JobDrivesService service;

    public JobDrivesController(JobDrivesService service) {
        this.service = service;
    }

    @PostMapping
    public JobDrives createJobDrive(@RequestBody JobDrives job) {
        return service.createJobDrive(job);
    }

    @GetMapping
    public List<JobDrives> ViewAll() {
        return service.ViewAll();
    }

    @GetMapping("/{id}")
    public JobDrives getJobDrives(@PathVariable Long id) {
        return service.getJobDrives(id);
    }

    @PutMapping("/{driveId}")
    public JobDrives updateJobDrives(@RequestBody UpdateDrives drive, @PathVariable Long driveId) {
        return service.updateJobDrives(drive, driveId);
    }

    @DeleteMapping("/{driveId}")
    public String deleteJobDrives(@PathVariable Long driveId) {
        return service.deleteJobDrives(driveId);
    }

    @GetMapping("/verified-students")
    public List<Students> getVerifiedStudents() {
        return service.getVerifiedStudents();
    }

    @GetMapping("/students/eligible")
    public List<EligibleStudentDTO> getEligibleStudents() {
        return service.getEligibleStudents();
    }
}
