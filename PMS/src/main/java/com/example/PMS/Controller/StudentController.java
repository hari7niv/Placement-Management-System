package com.example.PMS.Controller;

import java.util.List;

import com.example.PMS.DTO.UpdateProfileRequest;
import org.springframework.web.bind.annotation.*;

import com.example.PMS.DTO.LoginRequest;
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
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest entity) {
        return service.login(entity.getEmail(), entity.getPassword());
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
}
