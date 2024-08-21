package com.management.farm.Model.ReceiptsModel;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Receipt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate date;

    private int quantity;

    private double unitPrice;

    private double totalPrice;

    // Additional field for any notes or signing later
    private String notes;
}

