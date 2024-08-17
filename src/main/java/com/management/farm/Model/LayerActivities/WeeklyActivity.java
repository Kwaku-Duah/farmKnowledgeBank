package com.management.farm.Model.LayerActivities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class WeeklyActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private LocalDate startDate;

    private LocalDate endDate;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "weekly_activity_id")
    private List<LayerActivity> dailyActivities;

    private String weeklyComments;

    @ManyToOne
    @JoinColumn(name = "monthly_activity_id")
    
    private MonthlyActivity monthlyActivity;

    
   
}
