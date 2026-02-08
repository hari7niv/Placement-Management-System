package com.example.PMS.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
@RestController
@RequestMapping("/application/status")
=======
import com.example.PMS.Service.ApplicationStatusService;

@RestController
@RequestMapping("/application-status")
>>>>>>> 0d43d31c41602216e5e0a2e306370e62501b1cd1
public class ApplicationStatusController {

    final ApplicationStatusService service;
    public ApplicationStatusController(ApplicationStatusService service){
        this.service  = service;
    }
    

}
