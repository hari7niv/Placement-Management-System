package com.example.PMS.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.PMS.DTO.ApplicationDTO;
import com.example.PMS.Entity.Applications;
import com.example.PMS.Repository.ApplicationRepository;

@Service
public class ApplicationsService {

    private final ApplicationRepository appRepo;

    public ApplicationsService(ApplicationRepository appRepo) {
        this.appRepo = appRepo;
    }

    public String applyForJob(Applications app) {
        appRepo.save(app);
        return "Application Submitted";
    }

    public List<ApplicationDTO> getDet(Long id) {
        return appRepo.findById(id).stream()
                .map(user -> new ApplicationDTO(user.getAid(), user.getSid(), user.getApplied_at()))
                .collect(Collectors.toList());
    }
}
