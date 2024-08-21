package com.management.farm.Service.LayerServices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.farm.Model.LayerActivities.LayerActivity;
import com.management.farm.Model.LayerActivities.WeeklyActivity;
import com.management.farm.Repository.LayerRepositories.LayerRepository;
import com.management.farm.Repository.LayerRepositories.WeeklyRepository;

@Service
public class WeeklyService {
    @Autowired
    private WeeklyRepository weeklyRepository;

    @Autowired
    private LayerRepository layerRepository;


        public WeeklyActivity createWeeklyActivity(WeeklyActivity activity) {
        // Check if the activity or its daily activities are null
        if (activity == null) {
            throw new IllegalArgumentException("WeeklyActivity cannot be null");
        }

        List<LayerActivity> dailyActivities = activity.getDailyActivities();

        // Initialize dailyActivities if it is null
        if (dailyActivities == null) {
            dailyActivities = new ArrayList<>();
        }

        // Ensure each LayerActivity is saved or managed by the session
        for (LayerActivity dailyActivity : dailyActivities) {
            // Ensure each LayerActivity is not null before saving
            if (dailyActivity != null) {
                layerRepository.save(dailyActivity);
            }
        }

        // Save the WeeklyActivity after its daily activities are saved
        return weeklyRepository.save(activity);
    }


    public List<WeeklyActivity> getAllWeeklyActivities() {
        return weeklyRepository.findAll();
    }
}
