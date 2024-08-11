package com.management.farm.Service.LayerServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.farm.Model.LayerActivities.LayerActivity;
import com.management.farm.Repository.LayerRepositories.LayerRepository;

@Service
public class LayerService {
    @Autowired
    private LayerRepository layerRepository;

    // create daily activity
    public LayerActivity createDailyActivity(LayerActivity activity) {
        return layerRepository.save(activity);
    }

    // fetch daily activity
    public List<LayerActivity> getActivityByDate() {
        return layerRepository.findAll();
    }
}
