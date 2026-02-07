package com.example.PMS.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PMS.DTO.AdminLogin;
import com.example.PMS.DTO.AdminUpdateStudents;
import com.example.PMS.Entity.Admin;
import com.example.PMS.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Admin createAdmin(@RequestBody Admin admin) {
        return service.createAdmin(admin);
    }

    @PostMapping("/login")
    public String Adminlog(@RequestBody AdminLogin data) {
        return service.Adminlog(data.getEmail(), data.getPassword());
    }

    @GetMapping("/officers")
    public List<Admin> viewAdmins() {
        return service.viewAdmins();
    }

    @PostMapping("/students/{studentId}/verify")
    public String verifyStudent(@PathVariable Long studentId) {
        return service.verifyStudent(studentId);
    }

    @PostMapping("/students/{studentId}/reject")
    public String rejectStudent(@PathVariable Long studentId) {
        return service.rejectStudent(studentId);
    }

    @PutMapping("/students/update/{studentId}")
    public String updateStudent(@RequestBody AdminUpdateStudents data, Long id) {
        return service.updateStudent(data, id);
    }

}
