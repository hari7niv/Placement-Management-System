package com.example.PMS.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.PMS.DTO.LoginRequest;
import com.example.PMS.DTO.UpdateProfileRequest;
import com.example.PMS.Entity.Students;
import com.example.PMS.Repository.StudentRepository;

@Service
public class StudentService {
    final StudentRepository repo;
    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Students register(Students entity) {
        String encodedPassword = passwordEncoder.encode(entity.getPassword_hash());
        entity.setPassword_hash(encodedPassword);
        return repo.save(entity);
    }

    public String login(LoginRequest entity) {

        Students student = repo.findByEmail(entity.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(entity.getPassword(), student.getPassword_hash())) {
            throw new RuntimeException("Invalid email or password");
        }

        return "Login successful for: " + student.getFirst_name();
    }

    public List<Students> getAllStudents() {
        return repo.findAll();
    }

    public Students viewProfile(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public String UpdateProfile(UpdateProfileRequest data,Long id){
        Students student = repo.findById(id).orElseThrow(()->new RuntimeException("Cant find the id"));
        student.setFirst_name(data.getFirst_name());
        student.setBranch(data.getBranch());
        student.setCgpa(data.getCgpa());
        student.setLast_name(data.getLast_name());
        student.setPhone_number(data.getPhone_number());
        student.setResume_link(data.getResume_link());
        return "Success";
    }

}