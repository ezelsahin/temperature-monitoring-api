package com.qardio.temperaturemonitoring.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BodyTemperatureDTO {
    private Double temperature;

    private LocalDate requestDate;

    private LocalTime requestTime;
}
