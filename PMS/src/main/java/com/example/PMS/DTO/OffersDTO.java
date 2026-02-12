package com.example.PMS.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OffersDTO {

    private String offer_letter_url;
    private BigDecimal offered_package;
    private LocalDate offer_date;
    private Boolean accepted;
}
