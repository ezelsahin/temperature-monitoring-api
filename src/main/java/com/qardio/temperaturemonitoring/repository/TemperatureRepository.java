package com.qardio.temperaturemonitoring.repository;

import com.qardio.temperaturemonitoring.entity.BodyTemperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends JpaRepository<BodyTemperature, Integer> {

    /**
     * Calculates daily average temperature value for a given day with a specified query
     * @param date A given date value
     */
    @Query("SELECT AVG(temperature) FROM body_temperature bt WHERE bt.requestDate BETWEEN date(bt.requestDate) - 1 AND date(bt.requestDate)")
    Double findAverageTemperatureByDay(@Param("date") int date);

    /**
     * Calculates hourly average temperature value for a given hour and date with a specified query
     * @param date A given date value
     * @param time A given hour value
     */
    @Query("SELECT AVG(temperature) FROM body_temperature bt WHERE bt.requestDate BETWEEN " +
            "((date bt.requestDate + time bt.requestTime) + interval '-1 hours') AND (date bt.requestDate + time bt.requestHour)")
    Double findAverageTemperatureByHour(@Param("date") int date, @Param("time") int time);

}
