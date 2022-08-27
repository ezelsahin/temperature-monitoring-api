package com.qardio.temperaturemonitoring.dto.mapper;

import com.qardio.temperaturemonitoring.dto.BodyTemperatureDTO;
import com.qardio.temperaturemonitoring.entity.BodyTemperature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BodyTemperatureMapper {

    public static BodyTemperatureDTO toDTO (BodyTemperature bodyTemperature) {
        BodyTemperatureDTO bodyTemperatureDTO = new BodyTemperatureDTO();
        bodyTemperatureDTO.setTemperature()
        return
    }

    public static BodyTemperature toEntity (BodyTemperatureDTO bodyTemperatureDTO) {
        return
    }
}
