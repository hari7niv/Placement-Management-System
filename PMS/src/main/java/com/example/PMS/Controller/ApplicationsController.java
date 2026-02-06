package com.example.PMS.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/apply")
    public String applyForJob(@RequestBody Applications app) {
        return applicationService.applyForJob(app);
    }

    @GetMapping("/get/{aid}")
    public List<ApplicationDTO> getDet(@PathVariable Long aid) {
        return applicationService.getDet(aid);
    }

}
