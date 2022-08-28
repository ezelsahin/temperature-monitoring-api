package com.qardio.temperaturemonitoring.service.impl;

import com.qardio.temperaturemonitoring.entity.BodyTemperature;
import com.qardio.temperaturemonitoring.entity.DailyAverageTemperature;
import com.qardio.temperaturemonitoring.entity.HourlyAverageTemperature;
import com.qardio.temperaturemonitoring.repository.DailyAverageTemperatureRepository;
import com.qardio.temperaturemonitoring.repository.HourlyAverageTemperatureRepository;
import com.qardio.temperaturemonitoring.repository.TemperatureRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.qardio.temperaturemonitoring.dto.mapper.BodyTemperatureMapper.toDTO;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BodyTemperatureServiceImplTest {
    @Mock
    private TemperatureRepository temperatureRepository;

    @Mock
    private HourlyAverageTemperatureRepository hourlyAverageTemperatureRepository;

    @Mock
    private DailyAverageTemperatureRepository dailyAverageTemperatureRepository;

    @InjectMocks
    private BodyTemperatureServiceImpl bodyTemperatureServiceImpl;

    @Test
    void getHourlyTemperature() {
        //init
        HourlyAverageTemperature hourlyTemperature1 = new HourlyAverageTemperature(1,37.5,
                LocalDate.of(2022,8,23), LocalTime.of(13,34,23));
        HourlyAverageTemperature hourlyTemperature2 = new HourlyAverageTemperature(2,37.3,
                LocalDate.of(2022,8,23), LocalTime.of(13,39,42));
        List<HourlyAverageTemperature> expectedHourlyTemperatures = new ArrayList<>();
        expectedHourlyTemperatures.add(hourlyTemperature1);
        expectedHourlyTemperatures.add(hourlyTemperature2);

        //stub
        when(hourlyAverageTemperatureRepository.findAll()).thenReturn(expectedHourlyTemperatures);

        //then
        List<HourlyAverageTemperature> actualHourlyTemperatures = bodyTemperatureServiceImpl.getHourlyTemperature();
        Assert.assertEquals(expectedHourlyTemperatures.size(), actualHourlyTemperatures.size());
    }

    @Test
    void getDailyTemperature() {
        //init
        DailyAverageTemperature dailyTemperature1 = new DailyAverageTemperature(1,36.5,
                LocalDate.of(2022,8,21));
        DailyAverageTemperature dailyTemperature2 = new DailyAverageTemperature(2,36.8,
                LocalDate.of(2022,8,21));
        List<DailyAverageTemperature> expectedDailyTemperatures = new ArrayList<>();
        expectedDailyTemperatures.add(dailyTemperature1);
        expectedDailyTemperatures.add(dailyTemperature2);

        //stub
        when(dailyAverageTemperatureRepository.findAll()).thenReturn(expectedDailyTemperatures);

        //then
        List<DailyAverageTemperature> actualDailyTemperatures = bodyTemperatureServiceImpl.getDailyTemperature();
        Assert.assertEquals(expectedDailyTemperatures.size(), actualDailyTemperatures.size());
    }

    @Test
    void saveTemperature() {
        //init
        BodyTemperature expectedTemperature = new BodyTemperature(1, 37.1,
                LocalDate.of(2022,8,22), LocalTime.of(14,21,52));

        //stub
        when(temperatureRepository.save(expectedTemperature)).thenReturn(expectedTemperature);

        //then
        bodyTemperatureServiceImpl.saveTemperature(toDTO(expectedTemperature));

        //validation
        verify(temperatureRepository, times(1)).save(expectedTemperature);
    }

    @Test
    void saveDailyAverageTemperature() {
        //init
        Double expectedTemperature = 37.5;
        LocalDate expectedDate = LocalDate.of(2022,8,25);

        //stub
        Mockito.doNothing().when(dailyAverageTemperatureRepository).save(expectedTemperature, expectedDate);

        //then
        bodyTemperatureServiceImpl.saveDailyAverageTemperature(expectedTemperature, expectedDate);

        //validation
        verify(bodyTemperatureServiceImpl, times(1)).saveDailyAverageTemperature(expectedTemperature, expectedDate);
    }

    @Test
    void saveHourlyAverageTemperature() {
        //init
        Double expectedTemperature = 37.7;
        LocalDate expectedDate = LocalDate.of(2022,8,23);
        LocalTime expectedTime = LocalTime.of(15,36,55);

        //stub
        Mockito.doNothing().when(hourlyAverageTemperatureRepository).save(expectedTemperature, expectedDate, expectedTime);

        //then
        bodyTemperatureServiceImpl.saveHourlyAverageTemperature(expectedTemperature, expectedDate, expectedTime);

        //validation
        verify(bodyTemperatureServiceImpl, times(1)).saveHourlyAverageTemperature(expectedTemperature, expectedDate, expectedTime);
    }

    @Test
    void getDailyAverageTemperatureByDate() {
        //init
        LocalDate date = LocalDate.of(2022,8,24);
        DailyAverageTemperature dailyTemperature1 = new DailyAverageTemperature(1,37.5,
                LocalDate.of(2022,8,24));
        List<DailyAverageTemperature> expectedDailyTemperature = new ArrayList<>();
        expectedDailyTemperature.add(dailyTemperature1);

        //stub
        when(dailyAverageTemperatureRepository.getTemperature(date)).thenReturn(expectedDailyTemperature.get(0).getTemperature());

        //then
        Double actualDailyTemperature = bodyTemperatureServiceImpl.getDailyAverageTemperatureByDate(date);
        Assert.assertEquals(actualDailyTemperature, expectedDailyTemperature.get(0).getTemperature());
    }

    @Test
    void getHourlyAverageTemperatureByDateAndTime() {
        //init
        LocalDate date = LocalDate.of(2022,8,24);
        LocalTime time = LocalTime.of(18,44,15);
        HourlyAverageTemperature hourlyTemperature1 = new HourlyAverageTemperature(1,37.5,
                LocalDate.of(2022,8,24), LocalTime.of(18,44,15));
        List<HourlyAverageTemperature> expectedHourlyTemperature = new ArrayList<>();
        expectedHourlyTemperature.add(hourlyTemperature1);

        //stub
        when(hourlyAverageTemperatureRepository.getTemperature(date, time)).thenReturn(expectedHourlyTemperature.get(0).getTemperature());

        //then
        Double actualHourlyTemperature = bodyTemperatureServiceImpl.getHourlyAverageTemperatureByDateAndTime(date, time);
        Assert.assertEquals(actualHourlyTemperature, expectedHourlyTemperature.get(0).getTemperature());
    }
}
