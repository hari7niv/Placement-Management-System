package com.example.PMS.Service;

import com.example.PMS.DTO.UpdateCompany;
import com.example.PMS.Entity.Companies;
import com.example.PMS.Entity.JobDrives;
import com.example.PMS.Repository.CompaniesRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompaniesService {
    final CompaniesRepository repo;

    public CompaniesService(CompaniesRepository repo) {
        this.repo = repo;
    }

    public Companies addCompany(Companies companies){
        return repo.save(companies);
    }
    public List<Companies> viewCompanies(){
        return repo.findAll();
    }

    public Companies getCompanies(Long id){
        return repo.findById(id).orElseThrow(()->new RuntimeException("Company does not exit"));
    }
    @Transactional
    public void updateCompanies(UpdateCompany entity, Long id){
        Companies company = repo.findById(id).orElseThrow(()->new RuntimeException("Company does not exit"));
        company.setCompanyName(entity.getCompany_name());
        company.setWebsite(entity.getWebsite());
        company.setContact_email(entity.getContact_email());
        
    }

    public void  deleteCompany(Long id){
        repo.deleteById(id);
    }

    public Companies getCompanyByName(String name){
        return repo.findByCompanyName(name).orElseThrow(()->new RuntimeException("Invalid company name"));
    }

    public List<JobDrives> viewCompanyDrives(Long companyId){
        return repo.viewCompanyDrives(companyId);
    }
}
