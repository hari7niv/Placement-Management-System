package com.example.PMS.Service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.PMS.DTO.ApplicationStatusDTO;
import com.example.PMS.Entity.ApplicationStatus;
import com.example.PMS.Enum.Status;
import com.example.PMS.Repository.ApplicationStatusRepository;

@Service
public class ApplicationStatusService {
    final ApplicationStatusRepository repo;

    public ApplicationStatusService(ApplicationStatusRepository repo) {
        this.repo = repo;
    }

    public String addStatus(ApplicationStatus apps) {
        repo.save(apps);
        return "Added To DB";
    }

    public ApplicationStatusDTO getStatus(Long id) {
        return repo.findById(id).stream()
                .map(a -> a.toDTO())
                .findFirst()
                .orElse(null);

    }

    public String updateStatus(Long id, ApplicationStatusDTO app) {
        ApplicationStatus as = repo.findById(id).orElse(null);
        if (as != null) {
            as.setStatus(app.getStatus());
            as.setRemarks(app.getRemarks());
            repo.save(as);
            return "UPDATED";
        }
        return "ID NOT FOUND";
    }

    public String updateStatusOnly(Long id, String status) {
        ApplicationStatus as = repo.findById(id).orElse(null);
        if (as == null) {
            return "ID NOT FOUND";
        }
        // USING TRY CATCH
        // try{
        // as.setStatus(Status.valueOf(status));
        // return "UPDATED SUCCESSFULLY";
        // }catch(Exception e){
        // return "NOT UPDATED\nERROR: INVALID STATUS VALUE";
        // }

        // USING ARRAY.STREAM
        Optional<Status> stat = Arrays.stream(Status.values()).filter(a -> a.name().equalsIgnoreCase(status))
                .findFirst();
        if (stat.isPresent()) {
            as.setStatus(Status.valueOf(status.toUpperCase()));
            repo.save(as);
            return "UPDATED SUCCESSFULLY";
        }
        return "NOT UPDATED\nERROR: INVALID STATUS VALUE";
    }
}
