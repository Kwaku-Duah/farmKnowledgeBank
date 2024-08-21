package com.management.farm.Repository.ReceiptsRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.farm.Model.ReceiptsModel.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    
}
