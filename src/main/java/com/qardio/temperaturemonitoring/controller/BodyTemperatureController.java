package com.qardio.temperaturemonitoring.controller;

import com.qardio.temperaturemonitoring.dto.BodyTemperatureDTO;
import com.qardio.temperaturemonitoring.entity.BodyTemperature;
import com.qardio.temperaturemonitoring.entity.DailyAverageTemperature;
import com.qardio.temperaturemonitoring.entity.HourlyAverageTemperature;
import com.qardio.temperaturemonitoring.service.BodyTemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class BodyTemperatureController {

    @Autowired
    private BodyTemperatureService bodyTemperatureService;

    /**
     * A basic test method
     * @return Returns test message
     */
    @GetMapping
    public String test() {
        return "Testing is successful!";
    }

    /**
     * @return Returns hourly average temperatures on "/getHourly" url
     */
    @GetMapping("/getHourly")
    public List<HourlyAverageTemperature> getHourlyTemperature() {
        return bodyTemperatureService.getHourlyTemperature();
    }

    /**
     * @return Returns daily average temperatures on "/getDaily" url
     */
    @GetMapping("/getDaily")
    public List<DailyAverageTemperature> getDailyTemperature() {
        return bodyTemperatureService.getDailyTemperature();
    }

    /**
     * Adds new body temperature value on "/save" url and then checks if there are new temperature values on database
     * to calculate for new average temperature values
     * @param bodyTemperatureDTO A given body temperature dto entity with all parameters
     */
    @PutMapping("/save")
    public void saveTemperature(@RequestBody @Valid BodyTemperatureDTO bodyTemperatureDTO) {
        bodyTemperatureService.saveTemperature(bodyTemperatureDTO);

        bodyTemperatureService.checkAverageTemperatureData(LocalDate.now(), LocalTime.now());
    }

}
