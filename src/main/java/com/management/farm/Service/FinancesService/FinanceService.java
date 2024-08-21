package com.management.farm.Service.FinancesService;


import java.time.LocalDate;
import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.farm.Model.FinancesModel.Finance;
import com.management.farm.Model.FinancesModel.StartupCapital;
import com.management.farm.Repository.FinancesRepository.FinanceRepository;
import com.management.farm.Repository.FinancesRepository.StartupCapitalRepository;

@Service
public class FinanceService {
    
    @Autowired
    private FinanceRepository financeRepository;

    @Autowired
    private StartupCapitalRepository startupCapitalRepository;

    // Create financial record
    public Finance createFinanceRecord(Finance finance) {
        StartupCapital startupCapital = getStartupCapital();
        double totalExpenses = finance.getFeedingCost() + finance.getVaccineCost() +
                               finance.getLabourCost() + finance.getMiscellaneousCost();
        finance.setStartingCapital(startupCapital.getAmount());
        finance.setRemainingCapital(finance.getStartingCapital() - totalExpenses);
        return financeRepository.save(finance);
    }

    // Get all finance records
    public List<Finance> getFinanceRecords() {
        return financeRepository.findAll();
    }

    public Map<String, Double> getExpensesByYear(int year) {
        List<Map<String, Object>> results = financeRepository.getExpensesByYear(year);
        Map<String, Double> expensesByMonth = new LinkedHashMap<>();
    
        // All month names in order
        String[] monthsOrder = {
            "January", "February", "March", "April", "May", "June", 
            "July", "August", "September", "October", "November", "December"
        };
    
        // Initialize all months with zero expenses
        for (String month : monthsOrder) {
            expensesByMonth.put(month, 0.0);
        }
    
        // Update with actual data
        for (Map<String, Object> result : results) {
            String month = (String) result.get("month");
            Double totalExpenses = ((Number) result.get("totalExpenses")).doubleValue();
            expensesByMonth.put(month, totalExpenses);
        }
    
        return expensesByMonth;
    }
    
    
    

    // Get finance records by month
    public List<Finance> getFinanceRecordsByMonth(int year, int month) {
        LocalDate startDate = YearMonth.of(year, month).atDay(1);
        LocalDate endDate = YearMonth.of(year, month).atEndOfMonth();
        return financeRepository.findByDateBetween(startDate, endDate);
    }

    // Get total expenses for a specific month
    public double getTotalExpensesForMonth(int year, int month) {
        List<Finance> records = getFinanceRecordsByMonth(year, month);
        return records.stream()
                      .mapToDouble(f -> f.getFeedingCost() + f.getVaccineCost() + f.getLabourCost() + f.getMiscellaneousCost())
                      .sum();
    }

    // Set startup capital
    public StartupCapital setStartupCapital(double amount) {
        StartupCapital startupCapital = new StartupCapital();
        startupCapital.setAmount(amount);
        return startupCapitalRepository.save(startupCapital);
    }

    // Get startup capital
    public StartupCapital getStartupCapital() {
        return startupCapitalRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Startup capital not set"));
    }
}
