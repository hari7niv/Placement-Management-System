package com.example.PMS.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.PMS.DTO.OffersDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Offers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offer_id;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Applications application;

    private String offer_letter_url;

    private BigDecimal offered_package;

    private LocalDate offer_date;

    private Boolean accepted;

    public OffersDTO toDTO() {
        OffersDTO dto = new OffersDTO(offer_letter_url, offered_package, offer_date, accepted);
        return dto;
    }
}
