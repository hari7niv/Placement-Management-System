package com.example.PMS.Service;


import org.springframework.stereotype.Service;

import com.example.PMS.Repository.ApplicationStatusRepository;

@Service
public class ApplicationStatusService {
    final ApplicationStatusRepository repo;

    public ApplicationStatusService(ApplicationStatusRepository repo) {
        this.repo = repo;
    }

    
    
}
