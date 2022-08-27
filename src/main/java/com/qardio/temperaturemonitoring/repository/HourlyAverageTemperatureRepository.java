package com.qardio.temperaturemonitoring.repository;

import com.qardio.temperaturemonitoring.entity.HourlyAverageTemperature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface HourlyAverageTemperatureRepository extends JpaRepository<HourlyAverageTemperature, Integer> {

    void save(Double averageTemperature, LocalDate date, LocalTime time);

    Double getTemperature (LocalDate date, LocalTime time);

}
