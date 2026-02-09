package com.example.PMS.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PMS.DTO.OffersDTO;
import com.example.PMS.Entity.Offers;
import com.example.PMS.Service.OffersService;

@RestController
@RequestMapping
public class OffersController {
    final OffersService os;

    public OffersController(OffersService os) {
        this.os = os;
    }

    @PostMapping("/create")
    public Offers create(@RequestBody Offers of) {
        return os.create(of);
    }

    @GetMapping("/get/{oid}")
    public OffersDTO getOff(@PathVariable Long oid) {
        return os.getOff(oid);
    }

    @PutMapping("/update/{oid}")
    public String update(@PathVariable Long oid, @RequestBody Offers off) {
        return os.update(oid, off);
    }

    @DeleteMapping("/del/{oid}")
    public String delete(@PathVariable Long oid) {
        return os.delete(oid);
    }
}
