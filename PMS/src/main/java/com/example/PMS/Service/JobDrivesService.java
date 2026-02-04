package com.example.PMS.Service;


import com.example.PMS.Entity.JobDrives;
import com.example.PMS.Repository.JobDrivesRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.View;
import java.util.List;

@Service
public class JobDrivesService {
    final JobDrivesRepository repo;

    public JobDrivesService(JobDrivesRepository repo) {
        this.repo = repo;
    }

    public JobDrives createJobDrive(JobDrives job){
        return repo.save(job);
    }
    public List<JobDrives> ViewAll(){
        return repo.findAll();
    }
    public JobDrives getJobDrives(Long id){
        return repo.findById(id).orElseThrow(()->new RuntimeException("Job Drives not found"));
    }
    public JobDrives updateJobDrives(UpdateDrives drive, Long id){
        JobDrives job = repo.findById(id).orElseThrow(()->new RuntimeException("Job Drives not found"));
        job.setJob_role(drive.getJob_role());
        job.setPackage_lpa(drive.getPackage_lpa());
        job.setMin_cgpa(drive.getMin_cgpa());
        job.setEligible_branches(drive.getEligible_branches());
        return job;
    }
}
