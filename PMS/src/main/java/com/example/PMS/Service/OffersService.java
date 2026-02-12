package com.example.PMS.Service;

import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;

import com.example.PMS.DTO.OffersDTO;
import com.example.PMS.Entity.Offers;
import com.example.PMS.Repository.OffersRepository;

@RestController
public class OffersService {

    final OffersRepository repo;

    public OffersService(OffersRepository repo) {
        this.repo = repo;
    }

    public Offers create(Offers of) {
        return repo.save(of);
    }

    public OffersDTO getOff(Long oid) {
        return repo.findById(oid).orElse(null).toDTO();
    }

    public String update(Long oid, Offers os) {
        Optional<Offers> val = repo.findById(oid);
        if (val.isPresent()) {
            os.setOffer_id(val.get().getOffer_id());
            repo.save(os);
            return "UPDATED SUCCESSFULLY";
        }
        return "ID NOT FOUND";
    }

    public String delete(Long oid) {
        if (repo.existsById(oid)) {
            repo.deleteById(oid);
            return "DELETED SUCCESSFULLY";
        }
        return "ID NOT FOUND";
    }
}
