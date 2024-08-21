package com.management.farm.Controller.FinancesController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.management.farm.Model.FinancesModel.Finance;
import com.management.farm.Model.FinancesModel.StartupCapital;
import com.management.farm.Service.FinancesService.FinanceService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/finances")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @PostMapping("/start-capital")
    @Secured({ "ROLE_ADMIN" })
    public StartupCapital setStartupCapital(@RequestBody StartupCapital startupCapital) {
        return financeService.setStartupCapital(startupCapital.getAmount());
    }

    @GetMapping("/start-capital")
    @Secured({ "ROLE_ADMIN" })
    public StartupCapital getStartupCapital() {
        return financeService.getStartupCapital();
    }

    @PostMapping("/records")
    @Secured({ "ROLE_ADMIN" })
    public Finance createFinanceRecord(@RequestBody Finance finance) {
        return financeService.createFinanceRecord(finance);
    }

    @GetMapping("/records")
    @Secured({ "ROLE_ADMIN" })
    public List<Finance> getFinanceRecords() {
        return financeService.getFinanceRecords();
    }

    @GetMapping("/records/{year}/{month}")
    @Secured({ "ROLE_ADMIN" })
    public List<Finance> getFinanceRecordsByMonth(@PathVariable int year, @PathVariable int month) {
        return financeService.getFinanceRecordsByMonth(year, month);
    }

    @GetMapping("/expenses/{year}/{month}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Double> getTotalExpensesByMonth(
            @PathVariable("year") int year,
            @PathVariable("month") int month) {
        double totalExpenses = financeService.getTotalExpensesForMonth(month, year);
        return ResponseEntity.ok(totalExpenses);
    }

    // @GetMapping("/expenses/year/{year}")
    // @Secured({"ROLE_ADMIN"})
    // public ResponseEntity<Map<String, Double>> getYearlyExpenses(@PathVariable int year) {
    //     // Implement the logic to fetch and aggregate expenses by month
    //     System.out.println("Received year: " + year);
    //     System.out.println("Be above the error message");
    //     Map<String, Double> monthlyExpenses = financeService.getExpensesByYear(year);
    //     return ResponseEntity.ok(monthlyExpenses);
    // }

    @GetMapping("/expenses/year/{year}")
    public ResponseEntity<?> getYearlyExpenses(@PathVariable String year) {
        try {
            int yearInt = Integer.parseInt(year);
            System.out.println((yearInt));
            Map<String, Double> monthlyExpenses = financeService.getExpensesByYear(yearInt);
            return ResponseEntity.ok(monthlyExpenses);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid year format.");
        }
    }
    


    // @GetMapping("/expenses/{year}/{month}")
    // @Secured({"ROLE_ADMIN"})
    // public double getTotalExpensesForMonth(@PathVariable int year, @PathVariable
    // int month) {
    // return financeService.getTotalExpensesForMonth(year, month);
    // }
}
