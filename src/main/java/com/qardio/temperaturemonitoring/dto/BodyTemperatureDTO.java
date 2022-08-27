package com.qardio.temperaturemonitoring.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BodyTemperatureDTO {
    private Double temperature;

    private LocalDate requestDate;

    private LocalTime requestTime;
}
