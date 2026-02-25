package com.kce.weather.dto;

public class MonthlyStatsDTO {

    private Double maxTemp;
    private Double minTemp;
    private Double medianTemp;

    public MonthlyStatsDTO(
            Double maxTemp,
            Double minTemp,
            Double medianTemp){
        this.maxTemp=maxTemp;
        this.minTemp=minTemp;
        this.medianTemp=medianTemp;
    }

    public Double getMaxTemp(){ return maxTemp; }
    public Double getMinTemp(){ return minTemp; }
    public Double getMedianTemp(){ return medianTemp; }
}