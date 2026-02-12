package com.example.PMS.Controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PMS.DTO.UpdateCompany;
import com.example.PMS.Entity.Companies;
import com.example.PMS.Entity.JobDrives;
import com.example.PMS.Service.CompaniesService;

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
 
    @GetMapping("/{id}")
    public Companies getCompanies(@PathVariable Long id){
        return service.getCompanies(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void>  updateCompanies(@RequestBody UpdateCompany entity, @PathVariable Long id){
        
        service.updateCompanies(entity,id);
                return ResponseEntity.noContent().build();                    
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id){
        service.deleteCompany(id);
                return ResponseEntity.noContent().build();                    

    }

    @GetMapping("/search/{name}")
    public Companies getCompanyByName(@PathVariable String name){
        return service.getCompanyByName(name);
        
    }

    @GetMapping("/{companyId}/drives")
    public List<JobDrives> viewCompanyDrives(@PathVariable Long companyId){
        return service.viewCompanyDrives(companyId);
    }

}

