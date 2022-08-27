package com.qardio.temperaturemonitoring.repository;

import com.qardio.temperaturemonitoring.entity.DailyAverageTemperature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DailyAverageTemperatureRepository extends JpaRepository<DailyAverageTemperature, Integer> {

    void save (Double averageTemperature, LocalDate date);

    Double getTemperature (LocalDate date);
}

