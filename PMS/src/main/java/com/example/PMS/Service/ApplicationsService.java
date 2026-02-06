package com.example.PMS.Service;

import com.example.PMS.DTO.*;
import com.example.PMS.Entity.Applications;
import com.example.PMS.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
                .map(user -> new ApplicationDTO(user.getAid(), user.getStudent().getStudent_id(), user.getApplied_at()))
                .collect(Collectors.toList());
    }

    public String updateApplication(Long aid, Applications app) {
        // return appRepo.findById(aid).map(obj->{
        // obj.setStudent(app.getStudent());
        // obj.setDrive(app.getDrive());
        // appRepo.save(obj);
        // return "Updated Successfully";
        // }).orElse("ERROR: ID NOT FOUND");

        // OR

        if (appRepo.findById(aid).isEmpty()) {
            return "ERROR: ID NOT FOUND";
        } else {
            Applications obj = appRepo.findById(aid).get();
            obj.setStudent(app.getStudent());
            obj.setDrive(app.getDrive());
            appRepo.save(obj);
            return "Updated Successfully";
        }

    }
}
