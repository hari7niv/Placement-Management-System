package com.example.PMS.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.example.PMS.Entity.Applications;
import com.example.PMS.Entity.JobDrives;
import com.example.PMS.Entity.Students;
import com.example.PMS.Service.JobDrivesService;

@PreAuthorize("hasRole('ADMIN')")
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
    public ResponseEntity<Void> updateJobDrives(@RequestBody UpdateDrives drive, @PathVariable Long driveId) {
        service.updateJobDrives(drive, driveId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{driveId}")
    public ResponseEntity<Void> deleteJobDrives(@PathVariable Long driveId) {
         service.deleteJobDrives(driveId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/Un-verified-students")
    public List<Students> getUnVerifiedStudents() {
        return service.getUnVerifiedStudents();
    } 

    @GetMapping("/students/eligible/{id}")
    public List<EligibleStudentDTO> getEligibleStudents(@PathVariable Long id) {
        return service.getEligibleStudents(id);
    }

    @GetMapping("/active")
     public List<JobDrives> getActive(){
        return service.getActive();
     }

     @PutMapping("/{id}/close")
     public JobDrives closeDrive(@PathVariable Long id){
        return service.closeDrive(id);
     }

     @GetMapping("/{driveId}/applications")
     public List<Applications> findByDrive_Drive_id(@PathVariable Long driveId){
        return service.findByDrive_Drive_id(driveId);
     }
}
