package com.qardio.temperaturemonitoring.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DailyAverageTemperatureDTO {
    private Double averageTemperature;
    private LocalDateTime day;
}