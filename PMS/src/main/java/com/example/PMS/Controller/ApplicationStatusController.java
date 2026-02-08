package com.example.PMS.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PMS.Service.ApplicationStatusService;

@RestController
@RequestMapping("/application-status")
public class ApplicationStatusController {

    final ApplicationStatusService service;
    public ApplicationStatusController(ApplicationStatusService service){
        this.service  = service;
    }
    

}
