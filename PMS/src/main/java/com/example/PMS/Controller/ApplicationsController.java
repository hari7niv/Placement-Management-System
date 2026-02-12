package com.example.PMS.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.PMS.DTO.*;

import com.example.PMS.Entity.Applications;
import com.example.PMS.Service.ApplicationsService;

@RestController
@RequestMapping("/application")
public class ApplicationsController {

    private final ApplicationsService applicationService;

    public ApplicationsController(ApplicationsService applicationService) {
        this.applicationService = applicationService;
    }
    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/apply")
    public String applyForJob(@RequestBody Applications app) {
        return applicationService.applyForJob(app);
    }
    
    @GetMapping("/get/{aid}")
    public List<ApplicationDTO> getDet(@PathVariable Long aid) {
        return applicationService.getDet(aid);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{aid}")
    public String updateApplication(@PathVariable Long aid, @RequestBody Applications app) {
        return applicationService.updateApplication(aid, app);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{aid}")
    public String delete(@PathVariable Long aid) {
        return applicationService.delete(aid);
    }
}
