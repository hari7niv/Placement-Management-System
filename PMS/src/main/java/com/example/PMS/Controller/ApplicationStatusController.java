package com.example.PMS.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PMS.DTO.ApplicationStatusDTO;
import com.example.PMS.Entity.ApplicationStatus;
import com.example.PMS.Service.ApplicationStatusService;

@RestController
@RequestMapping("/application/status")
public class ApplicationStatusController {

    final ApplicationStatusService apps;

    public ApplicationStatusController(ApplicationStatusService apps) {
        this.apps = apps;
    }

    @PostMapping("/add")
    public String addStatus(@RequestBody ApplicationStatus app) {
        return apps.addStatus(app);
    }

    @GetMapping("/get/{status_id}")
    public ApplicationStatusDTO getStatus(@PathVariable Long status_id) {
        return apps.getStatus(status_id);
    }

    @PutMapping("/update/{status_id}")
    public String updateStatus(@PathVariable Long status_id, @RequestBody ApplicationStatusDTO app) {
        return apps.updateStatus(status_id, app);
    }

    @PutMapping("/update/status/{status_id}")
    public String updateStatusOnly(@PathVariable Long status_id, @RequestParam String status) {
        return apps.updateStatusOnly(status_id, status);
    }
}
