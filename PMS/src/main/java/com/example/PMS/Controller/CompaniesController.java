package com.example.PMS.Controller;


import com.example.PMS.DTO.UpdateCompany;
import com.example.PMS.Entity.Companies;
import com.example.PMS.Entity.JobDrives;
import com.example.PMS.Service.CompaniesService;

import org.springframework.data.repository.query.Param;
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
    public String  updateCompanies(@RequestBody UpdateCompany entity, @PathVariable Long id){
        return service.updateCompanies(entity,id);
    }

    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable Long id){
        return service.deleteCompany(id);
    }

    @GetMapping("/search/{name}")
    public Companies getCompanyByName(@PathVariable String name){
        return service.getCompanyByName(name);
    }

    @GetMapping("/{companyId}/drives")
    List<JobDrives> viewCompanyDrives(@PathVariable Long companyId){
        return service.viewCompanyDrives(companyId);
    }

}

