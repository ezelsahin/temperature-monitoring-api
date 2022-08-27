package com.qardio.temperaturemonitoring.dto.mapper;

import com.qardio.temperaturemonitoring.dto.HourlyAverageTemperatureDTO;
import com.qardio.temperaturemonitoring.entity.HourlyAverageTemperature;
import org.mapstruct.Mapper;

@Mapper
public interface HourlyAverageTemperatureMapper {
    HourlyAverageTemperatureDTO toDTO(HourlyAverageTemperature entity);

    HourlyAverageTemperature toEntity(HourlyAverageTemperatureDTO dto);
}
