package com.example.PMS.Controller;

import com.example.PMS.DTO.AdminLogin;
import com.example.PMS.DTO.AdminUpdateStudents;
import com.example.PMS.Entity.Admin;
import com.example.PMS.Service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("officers")
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
