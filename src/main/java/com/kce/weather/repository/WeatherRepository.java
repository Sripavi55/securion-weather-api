package com.kce.weather.repository;

import com.kce.weather.entity.WeatherData;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WeatherRepository
        extends JpaRepository<WeatherData,Long> {

    List<WeatherData> findByDateBetween(
            LocalDate start,
            LocalDate end
    );

    @Query("""
        SELECT w FROM WeatherData w
        WHERE MONTH(w.date)=:month
        AND YEAR(w.date)=:year
    """)
    List<WeatherData> findByMonthYear(
            @Param("month") int month,
            @Param("year") int year);
}