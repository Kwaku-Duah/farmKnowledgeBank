package com.management.farm.Repository.LayerRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.management.farm.Model.LayerActivities.MonthlyActivity;
import java.time.YearMonth;

public interface MonthlyActivityRepository extends JpaRepository<MonthlyActivity, Long> {
    MonthlyActivity findByMonthYear(YearMonth monthYear);
}
