package com.example.PMS.Controller;

import java.util.List;

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
    public String verify(@RequestParam String token) {
        return service.verify(token);                    
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest entity) {
        return service.login(entity);
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
    public String UpdateProfile(UpdateProfileRequest data,@PathVariable Long id){
        return service.UpdateProfile(data,id);
    }
    @GetMapping("{id}/drives/eligible")
    public List<Companies> getEligibleCompanies(@PathVariable Long id){
        return service.getEligibleCompanies(id);
    }
}
