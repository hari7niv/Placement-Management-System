package com.example.PMS.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<Void> verify(@RequestParam String token) {
        service.verify(token);
        return ResponseEntity.noContent().build();                    
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest entity) {
        return ResponseEntity.ok(service.login(entity));
    }
    @GetMapping("/list")
    public List<Students> get(){
        return service.getAllStudents();
    }

    @GetMapping("/profile/{email}")
    public Students viewProfile(@PathVariable String email){
        return service.viewProfile(email);
    }

    @PutMapping("/profile/update/{id}")
    public ResponseEntity<String> UpdateProfile(@RequestBody UpdateProfileRequest data,@PathVariable Long id){
        service.UpdateProfile(data,id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("{id}/drives/eligible")
    public List<Companies> getEligibleCompanies(@PathVariable Long id){
        return service.getEligibleCompanies(id);
    }
}
