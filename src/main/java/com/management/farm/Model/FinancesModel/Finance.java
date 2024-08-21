package com.management.farm.Model.FinancesModel;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Finance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double startingCapital;

    private double feedingCost;

    private double vaccineCost;

    private double labourCost;

    private double remainingCapital;

    private double miscellaneousCost;

 


}
