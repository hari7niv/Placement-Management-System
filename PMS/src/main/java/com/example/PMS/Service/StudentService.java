package com.example.PMS.Service;

import java.util.List;

import com.example.PMS.DTO.UpdateProfileRequest;
import org.springframework.stereotype.Service;

import com.example.PMS.Entity.Students;
import com.example.PMS.Repository.StudentRepository;

@Service
public class StudentService {
    final StudentRepository repo;
    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Students register(Students entity) {
        return repo.save(entity);
    }

   public String login(String email, String password) {

    Students student = repo.findByEmail(email).orElseThrow(() -> new RuntimeException("Student not found"));
    if (!password.equals(student.getPassword_hash())) {
        throw new RuntimeException("Invalid email or password");
    }

    return "Login successful for: " + student.getEmail();
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
