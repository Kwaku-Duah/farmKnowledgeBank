package com.management.farm.Model.FinancesModel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class StartupCapital {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double amount;
}
