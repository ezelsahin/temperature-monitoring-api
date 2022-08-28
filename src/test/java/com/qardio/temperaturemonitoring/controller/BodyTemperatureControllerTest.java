package com.qardio.temperaturemonitoring.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.qardio.temperaturemonitoring.entity.BodyTemperature;
import com.qardio.temperaturemonitoring.entity.DailyAverageTemperature;
import com.qardio.temperaturemonitoring.entity.HourlyAverageTemperature;
import com.qardio.temperaturemonitoring.exception.GenericExceptionHandler;
import com.qardio.temperaturemonitoring.service.BodyTemperatureService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.qardio.temperaturemonitoring.dto.mapper.BodyTemperatureMapper.toDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
public class BodyTemperatureControllerTest {
    private MockMvc mvc;

    @InjectMocks
    private BodyTemperatureController bodyTemperatureController;

    @Mock
    private BodyTemperatureService bodyTemperatureService;

    @BeforeEach
    public void setup()
    {
        mvc = MockMvcBuilders.standaloneSetup(bodyTemperatureController)
                .setControllerAdvice(new GenericExceptionHandler())
                .build();
    }

    private List<BodyTemperature> getTestTemperatures() {
        List<BodyTemperature> temperatures = new ArrayList<>();
        BodyTemperature temperature1 = new BodyTemperature(1, 37.2,
                LocalDate.of(2022,8,25), LocalTime.of(15,17,22));
        BodyTemperature temperature2 = new BodyTemperature(2, 36.8,
                LocalDate.of(2022,8,25), LocalTime.of(15,32,33));
        BodyTemperature temperature3 = new BodyTemperature(3, 37.0,
                LocalDate.of(2022,8,25), LocalTime.of(15,47,35));
        temperatures.add(temperature1);
        temperatures.add(temperature2);
        temperatures.add(temperature3);

        return temperatures;
    }

    private List<HourlyAverageTemperature> getHourlyTestTemperatures() {
        List<HourlyAverageTemperature> hourlyTemperatures = new ArrayList<>();
        HourlyAverageTemperature temperature1 = new HourlyAverageTemperature(1, 37.5,
                LocalDate.of(2022,8,25), LocalTime.of(16,24,22));
        HourlyAverageTemperature temperature2 = new HourlyAverageTemperature(2, 37.7,
                LocalDate.of(2022,8,25), LocalTime.of(16,29,33));
        hourlyTemperatures.add(temperature1);
        hourlyTemperatures.add(temperature2);

        return hourlyTemperatures;
    }

    private List<DailyAverageTemperature> getDailyTestTemperatures() {
        List<DailyAverageTemperature> dailyTemperatures = new ArrayList<>();
        DailyAverageTemperature temperature1 = new DailyAverageTemperature(1, 36.2,
                LocalDate.of(2022,8,27));
        DailyAverageTemperature temperature2 = new DailyAverageTemperature(2, 36.4,
                LocalDate.of(2022,8,27));
        dailyTemperatures.add(temperature1);
        dailyTemperatures.add(temperature2);

        return dailyTemperatures;
    }

    @Test
    void getHourlyTemperature() throws Exception {
        // init
        List<HourlyAverageTemperature> expectedHourlyTemperatures = getHourlyTestTemperatures();

        // stub
        when(bodyTemperatureController.getHourlyTemperature()).thenReturn(expectedHourlyTemperatures);

        MockHttpServletResponse response = mvc.perform(get("/api/getHourly")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<HourlyAverageTemperature> actualHourlyTemperatures = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<HourlyAverageTemperature>>() {});
        assertEquals(expectedHourlyTemperatures.size(), actualHourlyTemperatures.size());
    }

    @Test
    void getDailyTemperature() throws Exception {
        // init
        List<DailyAverageTemperature> expectedDailyTemperatures = getDailyTestTemperatures();

        // stub
        when(bodyTemperatureController.getDailyTemperature()).thenReturn(expectedDailyTemperatures);

        MockHttpServletResponse response = mvc.perform(get("/api/getDaily")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<DailyAverageTemperature> actualDailyTemperatures = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<DailyAverageTemperature>>() {});
        assertEquals(expectedDailyTemperatures.size(), actualDailyTemperatures.size());
    }

    @Test
    void saveTemperature() throws Exception {
        // init
        List<BodyTemperature> expectedTemperatures = getTestTemperatures();
        BodyTemperature nextTemperature = new BodyTemperature(4,37.5,
                LocalDate.of(2022,8,25), LocalTime.of(15,52,28));
        expectedTemperatures.add(nextTemperature);

        // stub
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedTemperatureJsonStr = ow.writeValueAsString(nextTemperature);
        Mockito.doNothing().when(bodyTemperatureService).saveTemperature(toDTO(nextTemperature));

        MockHttpServletResponse response = mvc.perform(put("/api/save")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedTemperatureJsonStr))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assert.assertEquals(expectedTemperatures.get(4), nextTemperature);
    }
}
