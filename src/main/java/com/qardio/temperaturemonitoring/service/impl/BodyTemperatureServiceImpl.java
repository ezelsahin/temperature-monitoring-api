package com.qardio.temperaturemonitoring.service.impl;

import com.qardio.temperaturemonitoring.dto.BodyTemperatureDTO;
import com.qardio.temperaturemonitoring.entity.BodyTemperature;
import com.qardio.temperaturemonitoring.entity.DailyAverageTemperature;
import com.qardio.temperaturemonitoring.entity.HourlyAverageTemperature;
import com.qardio.temperaturemonitoring.repository.DailyAverageTemperatureRepository;
import com.qardio.temperaturemonitoring.repository.HourlyAverageTemperatureRepository;
import com.qardio.temperaturemonitoring.repository.TemperatureRepository;
import com.qardio.temperaturemonitoring.service.BodyTemperatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Contains CRUD operations methods
 */
@Service
@RequiredArgsConstructor
public class BodyTemperatureServiceImpl implements BodyTemperatureService {

    @Autowired
    public HourlyAverageTemperatureRepository hourlyAverageTemperatureRepository;

    @Autowired
    public DailyAverageTemperatureRepository dailyAverageTemperatureRepository;

    @Autowired
    public TemperatureRepository temperatureRepository;

    /**
     * Gets all hourly average temperature entities from database
     * @return Returns all hourly average temperature entities in database as List<>
     */
    @Override
    public List<HourlyAverageTemperature> getHourlyTemperature()
    {
        return hourlyAverageTemperatureRepository.findAll();
    }

    /**
     * Gets all daily average temperature entities from database
     * @return Returns all daily average temperature entities in database as List<>
     */
    @Override
    public List<DailyAverageTemperature> getDailyTemperature()
    {
        return dailyAverageTemperatureRepository.findAll();
    }

    /**
     * Adds given body temperature entity to database
     * @param bodyTemperatureDTO A given body temperature dto entity with all parameters
     */
    @Override
    public void saveTemperature(BodyTemperatureDTO bodyTemperatureDTO){
        temperatureRepository.save(toEntity(bodyTemperatureDTO));
    }

    /**
     * Calculates daily average temperature value for given day and saves that value into database if it doesn't exist
     * @return Returns calculated daily average temperature value
     */
    public Double calculateDailyAverageTemperature(LocalDate date) {
        Double dailyAverageTemperature = temperatureRepository.findAverageTemperatureByDay(date.getDayOfYear());

        if(getDailyAverageTemperatureByDate(date) == null) {
            saveDailyAverageTemperature(dailyAverageTemperature, date);
        }
        return dailyAverageTemperature;
    }

    /**
     * Adds given daily average temperature entity to database
     * @param dailyAverageTemperature A calculated daily average temperature value
     * @param date A given date value
     */
    public void saveDailyAverageTemperature(Double dailyAverageTemperature, LocalDate date){
        dailyAverageTemperatureRepository.save(dailyAverageTemperature, date);
    }

    /**
     * Calculates hourly average temperature value for given hour and saves that value into database if it doesn't exist
     * @return Returns calculated hourly average temperature value
     */
    public Double calculateHourlyAverageTemperature(LocalDate date, LocalTime time) {
        Double hourlyAverageTemperature = temperatureRepository.findAverageTemperatureByHour(date.getDayOfYear(), time.getHour());

        if(getHourlyAverageTemperatureByDateAndTime(date, time) == null) {
            saveHourlyAverageTemperature(hourlyAverageTemperature, date, time);
        }
        return hourlyAverageTemperature;
    }

    /**
     * Adds given hourly average temperature entity to database
     * @param hourlyAverageTemperature A calculated hourly average temperature value
     * @param date A given date value
     * @param time A given time value
     */
    public void saveHourlyAverageTemperature(Double hourlyAverageTemperature, LocalDate date, LocalTime time){
        hourlyAverageTemperatureRepository.save(hourlyAverageTemperature, date, time);
    }

    /**
     * Gets daily average temperature value from database for given date
     * @return Returns daily average temperature value
     */
    public Double getDailyAverageTemperatureByDate(LocalDate date){
        return dailyAverageTemperatureRepository.getTemperature(date);
    }

    /**
     * Gets hourly average temperature value from database for given date and time
     * @return Returns hourly average temperature value
     */
    public Double getHourlyAverageTemperatureByDateAndTime(LocalDate date, LocalTime time){
        return hourlyAverageTemperatureRepository.getTemperature(date, time);
    }

    /**
     * Checks if there is calculated average temperature values for given date and time
     * @param date A given date value
     * @param time A given time value
     */
    public void checkAverageTemperatureData(LocalDate date, LocalTime time){
        calculateDailyAverageTemperature(date);
        calculateHourlyAverageTemperature(date, time);
    }
}
