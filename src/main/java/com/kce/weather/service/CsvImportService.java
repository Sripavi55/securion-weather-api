package com.kce.weather.service;

import com.kce.weather.entity.WeatherData;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvImportService {

    private final WeatherService weatherService;

    public CsvImportService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * Import CSV file from resources/data/testset.csv
     */
    public void importCSV() {

        try {

            System.out.println("🚀 Starting CSV Import...");

            InputStream is =
                    getClass().getResourceAsStream("/data/testset.csv");

            if (is == null) {
                throw new RuntimeException("CSV file not found!");
            }

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(is));

            String line;
            boolean header = true;

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm");

            List<WeatherData> batch = new ArrayList<>();

            while ((line = reader.readLine()) != null) {

                // skip header row
                if (header) {
                    header = false;
                    continue;
                }

                try {

                    String[] d = line.split(",");

                   
                    if (d.length < 6) {
                        continue;
                    }

                    LocalDateTime dt =
                            LocalDateTime.parse(d[0].trim(), formatter);

                    WeatherData w = new WeatherData();

                    w.setDate(dt.toLocalDate());
                    w.setTemperature(parseDoubleSafe(d[1]));
                    w.setHumidity(parseDoubleSafe(d[2]));
                    w.setPressure(parseDoubleSafe(d[3]));
                    w.setHeatIndex(parseDoubleSafe(d[4]));
                    w.setCondition(d[5].trim());

                    batch.add(w);

                } catch (Exception ex) {
                    System.out.println("⚠ Skipped row: " + line);
                }
            }

            weatherService.saveAll(batch);

            System.out.println("✅ CSV Imported Successfully");
            System.out.println("Total rows inserted: " + batch.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 
    private Double parseDoubleSafe(String value) {
        try {
            if (value == null || value.trim().isEmpty())
                return 0.0;
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0.0;
        }
    
    }
}