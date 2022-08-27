package com.qardio.temperaturemonitoring.service;

import com.qardio.temperaturemonitoring.dto.BodyTemperatureDTO;
import com.qardio.temperaturemonitoring.entity.BodyTemperature;
import com.qardio.temperaturemonitoring.entity.DailyAverageTemperature;
import com.qardio.temperaturemonitoring.entity.HourlyAverageTemperature;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Service for CRUD operations
 */
public interface BodyTemperatureService {
        /**
         * Calls all hourly average temperature entities from database
         */
        List<HourlyAverageTemperature> getHourlyTemperature();

        /**
         * Calls all daily average temperature entities from database
         */
        List<DailyAverageTemperature> getDailyTemperature();

        /**
         * Saves new body temperature entities to database
         * @param bodyTemperatureDTO A given body temperature dto entity with all parameters
         */
        void saveTemperature(BodyTemperatureDTO bodyTemperatureDTO);

        /**
         * Calls for a saved daily average temperature value with given date from database if it is exist
         * @param date A given date value
         */
        Double getDailyAverageTemperatureByDate(LocalDate date);

        /**
         * Calls for a saved hourly average temperature value with given date and time from database if it is exist
         * @param date A given date value
         * @param time A given time value
         */
        Double getHourlyAverageTemperatureByDateAndTime(LocalDate date, LocalTime time);

        /**
         * Checks if there are unused values for to calculate new average temperature values with given date and time
         * @param date A given date value
         * @param time A given time value
         */
        void checkAverageTemperatureData(LocalDate date, LocalTime time);

}
