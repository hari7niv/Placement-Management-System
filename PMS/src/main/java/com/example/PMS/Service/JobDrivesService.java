package com.example.PMS.Service;

import com.example.PMS.DTO.UpdateDrives;
import com.example.PMS.Entity.JobDrives;
import com.example.PMS.Entity.Students;
import com.example.PMS.Repository.JobDrivesRepository;
import com.example.PMS.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobDrivesService {
    final JobDrivesRepository repo;

    public JobDrivesService(JobDrivesRepository repo) {
        this.repo = repo;
    }

<<<<<<< HEAD
    public JobDrives createJobDrive(JobDrives job){
        job.setEligible_branches(
                job.getEligible_branches().replace(" ", ",")
        );
=======
    public JobDrives createJobDrive(JobDrives job) {
>>>>>>> cc03501fb93b7fc2896b565c98651e6027365612
        return repo.save(job);
    }

    public List<JobDrives> ViewAll() {
        return repo.findAll();
    }

    public JobDrives getJobDrives(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Job Drives not found"));
    }

    public JobDrives updateJobDrives(UpdateDrives drive, Long id) {
        JobDrives job = repo.findById(id).orElseThrow(() -> new RuntimeException("Job Drives not found"));
        job.setJob_role(drive.getJob_role());
        job.setPackage_lpa(drive.getPackage_lpa());
        job.setMin_cgpa(drive.getMin_cgpa());
        job.setEligible_branches(drive.getEligible_branches());
        repo.save(job);
        return job;
    }

    public String deleteJobDrives(Long id) {
        repo.deleteById(id);
        return "Deleted successfully";
    }

    @Autowired
    StudentRepository studentRepository;
<<<<<<< HEAD
    public List<Students> getVerifiedStudents(){
=======

    public List<Students> getEligibleStudents() {
>>>>>>> cc03501fb93b7fc2896b565c98651e6027365612
        return studentRepository.getVerifiedStudents();
    }

    public List<Students> getEligibleStudents(){
        return studentRepository.getEligibleStudents();
    }


}
