package com.kce.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.kce.weather.service.CsvImportService;

@SpringBootApplication
public class SecurionWeatherApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurionWeatherApiApplication.class, args);
    }

    @Bean
    CommandLineRunner run(CsvImportService csv){
        return args -> csv.importCSV();
    }
}