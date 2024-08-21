package com.management.farm.DTO.ReceiptDTos;

import lombok.*;

@Data
public class ReceiptRequest {
    private String name;
    private int quantity;
    private double unitPrice;
}
