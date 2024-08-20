package com.management.farm.Repository.FinancesRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.management.farm.Model.FinancesModel.Finance;

public interface FinanceRepository extends JpaRepository<Finance, Long> {
    List<Finance> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT new map(TRIM(TO_CHAR(f.date, 'Month')) AS month, COALESCE(SUM(f.feedingCost + f.vaccineCost + f.labourCost + f.miscellaneousCost), 0) AS totalExpenses) " +
    "FROM Finance f " +
    "WHERE EXTRACT(YEAR FROM f.date) = :year " +
    "GROUP BY EXTRACT(MONTH FROM f.date), TRIM(TO_CHAR(f.date, 'Month')) " +
    "ORDER BY EXTRACT(MONTH FROM f.date)")
List<Map<String, Object>> getExpensesByYear(@Param("year") int year);
}