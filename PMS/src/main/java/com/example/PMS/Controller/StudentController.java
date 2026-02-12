package com.example.PMS.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PMS.DTO.LoginRequest;
import com.example.PMS.DTO.UpdateProfileRequest;
import com.example.PMS.Entity.Companies;
import com.example.PMS.Entity.Students;
import com.example.PMS.Service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
    final StudentService service;
    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public Students register(@RequestBody Students entity) {
       
        return service.register(entity);
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String token) {
        service.verify(token);
        return ResponseEntity.ok("Account Verified");                   
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest entity) {
        return ResponseEntity.ok(service.login(entity));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/list")
    public List<Students> get(){
        return service.getAllStudents();
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/profile")
    public Students viewProfile(){
        String email = SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getName();
        return service.viewProfile(email);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PutMapping("/profile/update")
    public ResponseEntity<String> UpdateProfile(@RequestBody UpdateProfileRequest data){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Students student = service.getStudentsFromEmail(email);
        service.UpdateProfile(data,student.getStudent_id());
        return ResponseEntity.noContent().build();
    }
    
    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/drives/eligible")
    public List<Companies> getEligibleCompanies(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Students student = service.getStudentsFromEmail(email);
        return service.getEligibleCompanies(student.getStudent_id());
    }
}
