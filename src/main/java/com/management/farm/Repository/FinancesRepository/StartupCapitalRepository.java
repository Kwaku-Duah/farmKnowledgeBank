package com.management.farm.Repository.FinancesRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.farm.Model.FinancesModel.StartupCapital;

public interface StartupCapitalRepository extends JpaRepository<StartupCapital, Long> {
}
