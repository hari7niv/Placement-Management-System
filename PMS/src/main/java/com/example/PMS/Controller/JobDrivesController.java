package com.example.PMS.Controller;

import com.example.PMS.DTO.UpdateDrives;
import com.example.PMS.Entity.JobDrives;
import com.example.PMS.Entity.Students;
import com.example.PMS.Service.JobDrivesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
<<<<<<< HEAD

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

=======
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> cc03501fb93b7fc2896b565c98651e6027365612

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
    public JobDrives updateJobDrives(@RequestBody UpdateDrives drive, Long driveId) {
        return service.updateJobDrives(drive, driveId);
    }

    @DeleteMapping("/{driveId}")
    public String deleteJobDrives(Long driveId) {
        return service.deleteJobDrives(driveId);
    }

    @GetMapping("/verified-students")
<<<<<<< HEAD
    public List<Students> getVerifiedStudents(){
        return service.getVerifiedStudents();
    }

    @GetMapping("/students/eligible")
    public List<Students> getEligibleStudents(){
=======
    public List<Students> getEligibleStudents() {
>>>>>>> cc03501fb93b7fc2896b565c98651e6027365612
        return service.getEligibleStudents();
    }
}
