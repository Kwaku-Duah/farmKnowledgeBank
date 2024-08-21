package com.management.farm.Model.LayerActivities;

import java.time.YearMonth;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class MonthlyActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private YearMonth monthYear;  // Stores the month and year
    private String monthlyComment;

    @OneToMany(fetch = FetchType.LAZY)
    private List<WeeklyActivity> weeklyActivities;
}
