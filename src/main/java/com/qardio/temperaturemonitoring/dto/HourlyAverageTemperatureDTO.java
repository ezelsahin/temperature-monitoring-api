package com.qardio.temperaturemonitoring.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HourlyAverageTemperatureDTO {
    private Double averageTemperature;
    private LocalDateTime hour;
}
