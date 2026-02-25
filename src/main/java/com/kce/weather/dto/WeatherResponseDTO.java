package com.kce.weather.dto;

import java.time.LocalDate;

public class WeatherResponseDTO {

    private LocalDate date;
    private Double temperature;
    private Double humidity;
    private Double pressure;
    private Double heatIndex;
    private String condition;

    public WeatherResponseDTO(LocalDate date, Double temperature,
                              Double humidity, Double pressure,
                              Double heatIndex, String condition) {
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.heatIndex = heatIndex;
        this.condition = condition;
    }

    public LocalDate getDate() { return date; }
    public Double getTemperature() { return temperature; }
    public Double getHumidity() { return humidity; }
    public Double getPressure() { return pressure; }
    public Double getHeatIndex() { return heatIndex; }
    public String getCondition() { return condition; }
}