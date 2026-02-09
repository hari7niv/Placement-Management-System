package com.example.PMS.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.PMS.DTO.LoginRequest;
import com.example.PMS.DTO.UpdateProfileRequest;
import com.example.PMS.Entity.Companies;
import com.example.PMS.Entity.Students;
import com.example.PMS.Repository.StudentRepository;
import com.example.PMS.Security.JwtService;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
    final StudentRepository repo;
    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    EmailService emailSender;
    public Students register(Students entity) {
        String encodedPassword = passwordEncoder.encode(entity.getPassword_hash());
        entity.setPassword_hash(encodedPassword);
        String token = UUID.randomUUID().toString();
        entity.setEnabled(false);
        entity.setVerificationToken(token);
        String link = " http://localhost:8080/students/verify?token=" + token;
        emailSender.sendSimpleEmail(entity.getEmail(),"Verify Email",("Click the link to verify"+link));

        return repo.save(entity);
    }

    public void verify(String token) {
    Students user = repo.findByVerificationToken(token)
        .orElseThrow(() -> new RuntimeException("Invalid token"));

    user.setEnabled(true);
    user.setVerificationToken(null);
    repo.save(user);
    }

    @Autowired
    JwtService jwtService;

    public String login(LoginRequest entity) {
        Students student = repo.findByEmail(entity.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if(!student.isEnabled()){throw new RuntimeException("Verify your email first");}

        if (!passwordEncoder.matches(entity.getPassword(), student.getPassword_hash())) {
            throw new RuntimeException("Invalid email or password");
        }      

         return jwtService.generateToken(student.getEmail(), "STUDENT");
    }

    public List<Students> getAllStudents() {
        return repo.findAll();
    }

    public Students viewProfile(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Transactional
    public void  UpdateProfile(UpdateProfileRequest data,Long id){
        Students student = repo.findById(id).orElseThrow(()->new RuntimeException("Cant find the id"));
        student.setFirst_name(data.getFirst_name());
        student.setBranch(data.getBranch());
        student.setCgpa(data.getCgpa());
        student.setLast_name(data.getLast_name());
        student.setPhone_number(data.getPhone_number());
        student.setResume_link(data.getResume_link());
        
    }

    public List<Companies> getEligibleCompanies(Long student_id){
        return repo.getEligibleCompanies(student_id);
    }

}