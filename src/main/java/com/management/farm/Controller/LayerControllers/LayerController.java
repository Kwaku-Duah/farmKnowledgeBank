package com.management.farm.Controller.LayerControllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.management.farm.Model.LayerActivities.LayerActivity;
import com.management.farm.Service.LayerServices.LayerService;

@RestController
@RequestMapping("/api/layer")
public class LayerController {
    @Autowired
    private LayerService layerService;
    
    @PostMapping("/daily")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<LayerActivity> createDailyActivity(@RequestBody LayerActivity activity ){
        LayerActivity createdDailyActivity = layerService.createDailyActivity(activity);
        return ResponseEntity.ok(createdDailyActivity);
    }

    @GetMapping("/daily-activity")
    public ResponseEntity<List<LayerActivity>> getActivitiesByDate(){
        List<LayerActivity> activities = layerService.getActivityByDate();
        return ResponseEntity.ok(activities);
    }


}
