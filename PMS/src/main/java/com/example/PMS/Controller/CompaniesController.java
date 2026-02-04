package com.example.PMS.Controller;


import com.example.PMS.DTO.UpdateCompany;
import com.example.PMS.Entity.Companies;
import com.example.PMS.Service.CompaniesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
    final CompaniesService service;

    public CompaniesController(CompaniesService service) {
        this.service = service;
    }

    @PostMapping
    public Companies addCompanies(@RequestBody Companies company){
        return service.addCompany(company);
    }

    @GetMapping
    public List<Companies> viewCompanies(){
        return service.viewCompanies();
    }

    @PostMapping("/{id}")
    public Companies getCompanies(@PathVariable Long id){
        return service.getCompanies(id);
    }

    @PutMapping("/update/{id}")
    public String  updateCompanies(UpdateCompany entity, Long id){
        return service.updateCompanies(entity,id);
    }

}

