package com.qardio.temperaturemonitoring.dto.mapper;

import com.qardio.temperaturemonitoring.dto.DailyAverageTemperatureDTO;
import com.qardio.temperaturemonitoring.entity.DailyAverageTemperature;
import org.mapstruct.Mapper;

@Mapper
public interface DailyAverageTemperatureMapper {
    DailyAverageTemperatureDTO toDTO(DailyAverageTemperature entity);

    DailyAverageTemperature toEntity(DailyAverageTemperatureDTO dto);
}
