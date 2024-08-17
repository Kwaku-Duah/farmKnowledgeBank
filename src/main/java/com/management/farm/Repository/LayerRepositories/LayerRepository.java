package com.management.farm.Repository.LayerRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.*;
import com.management.farm.Model.LayerActivities.LayerActivity;

public interface LayerRepository extends JpaRepository<LayerActivity, Long> {
    List<LayerActivity> findByDate(LocalDate date);

    List<LayerActivity> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
