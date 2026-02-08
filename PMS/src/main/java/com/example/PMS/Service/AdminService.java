package com.example.PMS.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.PMS.DTO.AdminLogin;
import com.example.PMS.DTO.AdminUpdateStudents;
import com.example.PMS.Entity.Admin;
import com.example.PMS.Entity.Students;
import com.example.PMS.Repository.AdminRepository;
import com.example.PMS.Repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminService {
    final AdminRepository repo;

    public AdminService(AdminRepository repo) {
        this.repo = repo;
    }

   @Autowired
   private PasswordEncoder passwordEncoder;
    public Admin createAdmin(Admin admin) {
       String encodedPassword = passwordEncoder.encode(admin.getPassword_hash());
       admin.setPassword_hash(encodedPassword);
        return repo.save(admin);
    }

    public void  Adminlog(AdminLogin entity) {

        Admin admin = repo.findByEmail(entity.getEmail()).orElseThrow(() -> new RuntimeException("Admin not found "));
        if (!passwordEncoder.matches(entity.getPassword(), admin.getPassword_hash())) {
            throw new RuntimeException("Invalid email or password");
        }
    }

    public List<Admin> viewAdmins() {
        return repo.findAll();
    }

    @Autowired
    private StudentRepository studentRepository;

    public String verifyStudent(Long id) {
        Students students = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid Student Id"));
        students.setIs_verified(true);
        return "student " + students.getFirst_name() + " verified";
    }

    public void  rejectStudent(Long id) {
        Students students = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid Student Id"));
        students.setIs_verified(false);
    }
    
    @Transactional
    public void  updateStudent(AdminUpdateStudents data, Long id) {
        Students students = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid Student Id"));
        students.setBranch(data.getBranch());
        students.setCgpa(data.getCgpa());
        students.setBacklog_count(data.getBacklog_count());

    }

}
