package com.management.farm.Repository.LayerRepositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.management.farm.Model.LayerActivities.WeeklyActivity;

public interface WeeklyRepository extends JpaRepository<WeeklyActivity, Long> {

    @Query("SELECT w FROM WeeklyActivity w WHERE w.startDate >= :startDate AND w.endDate <= :endDate")
    List<WeeklyActivity> findByMonth(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
