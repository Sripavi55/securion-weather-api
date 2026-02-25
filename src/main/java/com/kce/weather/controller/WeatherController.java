package com.kce.weather.controller;

import com.kce.weather.dto.MonthlyStatsDTO;
import com.kce.weather.entity.WeatherData;
import com.kce.weather.service.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service){
        this.service=service;
    }

    // ✅ ALL DATA
    @GetMapping
    public List<WeatherData> all(){
        return service.getAll();
    }

    // ✅ SORTING
    @GetMapping("/sorted")
    public List<WeatherData> sorted(
            @RequestParam String field){
        return service.getSorted(field);
    }

    // ✅ FILTER DATE RANGE
    @GetMapping("/range")
    public List<WeatherData> range(
            @RequestParam String start,
            @RequestParam String end){

        return service.getBetween(
                LocalDate.parse(start),
                LocalDate.parse(end));
    }

    // ✅ MONTHLY STATS
    @GetMapping("/stats")
    public MonthlyStatsDTO stats(
            @RequestParam int month,
            @RequestParam int year){

        return service.monthlyStats(month,year);
    }
}