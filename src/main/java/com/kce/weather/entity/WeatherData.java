package com.kce.weather.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "weather_data")
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="weather_date")
    private LocalDate date;

    private Double temperature;
    private Double humidity;
    private Double pressure;

    @Column(name="heat_index")
    private Double heatIndex;

    @Column(name="weather_condition")
    private String condition;

    // getters & setters
    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public LocalDate getDate(){ return date; }
    public void setDate(LocalDate date){ this.date=date; }

    public Double getTemperature(){ return temperature; }
    public void setTemperature(Double temperature){ this.temperature=temperature; }

    public Double getHumidity(){ return humidity; }
    public void setHumidity(Double humidity){ this.humidity=humidity; }

    public Double getPressure(){ return pressure; }
    public void setPressure(Double pressure){ this.pressure=pressure; }

    public Double getHeatIndex(){ return heatIndex; }
    public void setHeatIndex(Double heatIndex){ this.heatIndex=heatIndex; }

    public String getCondition(){ return condition; }
    public void setCondition(String condition){ this.condition=condition; }
}