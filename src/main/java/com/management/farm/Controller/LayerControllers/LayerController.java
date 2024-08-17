package com.management.farm.Controller.LayerControllers;

import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.farm.Model.LayerActivities.LayerActivity;
import com.management.farm.Model.LayerActivities.MonthlyActivity;
import com.management.farm.Model.LayerActivities.WeeklyActivity;
import com.management.farm.Service.LayerServices.LayerService;
import com.management.farm.Service.LayerServices.MonthlyActivityService;
import com.management.farm.Service.LayerServices.WeeklyService;

@RestController
@RequestMapping("/api/layer")
public class LayerController {
    @Autowired
    private LayerService layerService;

    @Autowired
    private WeeklyService weeklyService;

    @Autowired
    private MonthlyActivityService monthlyActivityService;
    
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
    @PostMapping("/weekly")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<WeeklyActivity> createWeeklyActivity(@RequestBody WeeklyActivity activity) {
        WeeklyActivity createdWeeklyActivity = weeklyService.createWeeklyActivity(activity);
        return ResponseEntity.ok(createdWeeklyActivity);
    }

    @GetMapping("/weekly-activity")
    public ResponseEntity<List<WeeklyActivity>> getAllWeeklyActivities() {
        List<WeeklyActivity> activities = weeklyService.getAllWeeklyActivities();
        return ResponseEntity.ok(activities);
    }



    @PostMapping("/monthly")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<MonthlyActivity> createMonthlyActivity(@RequestBody MonthlyActivity monthlyActivity) {
        MonthlyActivity createdMonthlyActivity = monthlyActivityService.createMonthlyActivity(monthlyActivity);
        return ResponseEntity.ok(createdMonthlyActivity);
    }

    @GetMapping("/monthly-activity")
    public ResponseEntity<MonthlyActivity> getMonthlyActivity(@RequestParam String monthYear) {
        YearMonth ym = YearMonth.parse(monthYear);
        MonthlyActivity monthlyActivity = monthlyActivityService.getMonthlyActivity(ym);
        return ResponseEntity.ok(monthlyActivity);
    }


}
