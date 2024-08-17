package com.management.farm.Service.LayerServices;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.farm.Model.LayerActivities.MonthlyActivity;
import com.management.farm.Model.LayerActivities.WeeklyActivity;
import com.management.farm.Repository.LayerRepositories.MonthlyActivityRepository;
import com.management.farm.Repository.LayerRepositories.WeeklyRepository;

@Service
public class MonthlyActivityService {

    @Autowired
    private MonthlyActivityRepository monthlyActivityRepository;

    @Autowired
    private WeeklyRepository weeklyRepository;

    public MonthlyActivity getMonthlyActivity(YearMonth monthYear) {
        MonthlyActivity monthlyActivity = monthlyActivityRepository.findByMonthYear(monthYear);
        if (monthlyActivity != null) {
            LocalDate startDate = monthYear.atDay(1);  // Start of the month
            LocalDate endDate = monthYear.atEndOfMonth();  // End of the month
            List<WeeklyActivity> weeklyActivities = weeklyRepository.findByMonth(startDate, endDate);
            monthlyActivity.setWeeklyActivities(weeklyActivities);
        }
        return monthlyActivity;
    }

    public MonthlyActivity createMonthlyActivity(MonthlyActivity monthlyActivity) {
        // Ensure that the MonthlyActivity does not already exist for the given month and year
        if (monthlyActivityRepository.findByMonthYear(monthlyActivity.getMonthYear()) != null) {
            throw new IllegalArgumentException("Monthly activity already exists for this month and year.");
        }
        
        // Persist the new MonthlyActivity entity to the database
        return monthlyActivityRepository.save(monthlyActivity);
    }
  
}
