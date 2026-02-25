package com.kce.weather.service;

import com.kce.weather.dto.MonthlyStatsDTO;
import com.kce.weather.entity.WeatherData;
import com.kce.weather.repository.WeatherRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class WeatherService {

    private final WeatherRepository repo;

    public WeatherService(WeatherRepository repo){
        this.repo = repo;
    }

    public void saveAll(List<WeatherData> data){
        repo.saveAll(data);
    }

    public List<WeatherData> getAll(){
        return repo.findAll();
    }

    // ✅ SORTING
    public List<WeatherData> getSorted(String field){
        return repo.findAll(Sort.by(field));
    }

    // ✅ DATE FILTER
    public List<WeatherData> getBetween(
            LocalDate start,
            LocalDate end){
        return repo.findByDateBetween(start,end);
    }

    // ✅ MONTHLY STATS
    public MonthlyStatsDTO monthlyStats(int month,int year){

        List<WeatherData> list =
                repo.findByMonthYear(month,year);

        List<Double> temps = list.stream()
                .map(WeatherData::getTemperature)
                .filter(Objects::nonNull)
                .sorted()
                .toList();

        if(temps.isEmpty())
            return new MonthlyStatsDTO(0.0,0.0,0.0);

        double min = temps.get(0);
        double max = temps.get(temps.size()-1);

        double median;
        int size = temps.size();

        if(size%2==0)
            median=(temps.get(size/2-1)+temps.get(size/2))/2;
        else
            median=temps.get(size/2);

        return new MonthlyStatsDTO(max,min,median);
    }
}