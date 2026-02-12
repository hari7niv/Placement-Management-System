package com.example.PMS.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PMS.DTO.EligibleStudentDTO;
import com.example.PMS.DTO.UpdateDrives;
import com.example.PMS.Entity.Applications;
import com.example.PMS.Entity.JobDrives;
import com.example.PMS.Entity.Students;
import com.example.PMS.Repository.ApplicationRepository;
import com.example.PMS.Repository.JobDrivesRepository;
import com.example.PMS.Repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class JobDrivesService {

    final JobDrivesRepository repo;

    @Autowired
    StudentRepository studentRepository;

    public JobDrivesService(JobDrivesRepository repo) {
        this.repo = repo;
    }

    public JobDrives createJobDrive(JobDrives job){
        job.setEligible_branches(
                job.getEligible_branches().replace(" ", ",")
        );
        JobDrives jobs = repo.save(job);
        List<EligibleStudentDTO> students= studentRepository.getEligibleStudents(job.getCompany().getCompany_id());
        for (EligibleStudentDTO student : students) {
            String subject = student.getCompanyName() + " has posted a job";
            String body = student.getFirstName() + " " + student.getLastName() + ", you are eligible to apply in "
                    + student.getCompanyName() + " if you are interested in this company you may apply";
            emailSender.sendSimpleEmail(student.getEmail(), subject, body);
        }
        
        return jobs;
    }

    public List<JobDrives> ViewAll() {
        return repo.findAll();
    }

    public JobDrives getJobDrives(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Drives not found"));
    }

    @Transactional
    public void updateJobDrives(UpdateDrives drive, Long id) {

        JobDrives job = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Drives not found"));

        job.setJob_role(drive.getJob_role());
        job.setPackage_lpa(drive.getPackage_lpa());
        job.setMin_cgpa(drive.getMin_cgpa());
        job.setEligible_branches(drive.getEligible_branches());

    }

    public void deleteJobDrives(Long id) {
        repo.deleteById(id);
    }

    public List<Students> getUnVerifiedStudents() {
        return studentRepository.getUnVerifiedStudents();
    }

    @Autowired
    EmailService emailSender;

    public List<EligibleStudentDTO> getEligibleStudents(Long id) {

        return studentRepository.getEligibleStudents(id);
    }

    public List<JobDrives> getActive() {
        return repo.findAllByActiveTrue();
    }

    public JobDrives closeDrive(Long Id) {
        JobDrives drive = repo.findById(Id).orElseThrow(() -> new RuntimeException("Cant find the Id"));
        drive.setActive(false);
        repo.save(drive);
        return drive;
    }

    @Autowired
    ApplicationRepository applications;

    public List<Applications> findByDrive_Drive_id(Long driveId) {
        return applications.findByDrive_DriveId(driveId);
    }
}
