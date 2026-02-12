package com.example.PMS.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<Void> Adminlog(@RequestBody AdminLogin data) {
        service.Adminlog(data);
        return ResponseEntity.noContent().build();                    

    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/officers")
    public List<Admin> viewAdmins() {
        return service.viewAdmins();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/students/{studentId}/verify")
    public ResponseEntity<Void> verifyStudent(@PathVariable Long studentId) {
        service.verifyStudent(studentId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/students/{studentId}/reject")
    public ResponseEntity<Void> rejectStudent(@PathVariable Long studentId) {
        service.rejectStudent(studentId);
        return ResponseEntity.noContent().build();                    

    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/students/update/{id}")
    public ResponseEntity<Void> updateStudent(@RequestBody AdminUpdateStudents data,@PathVariable Long id) {
        service.updateStudent(data, id);
        return ResponseEntity.noContent().build();                    

    }

}
