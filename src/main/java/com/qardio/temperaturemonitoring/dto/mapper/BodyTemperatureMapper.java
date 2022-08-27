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
        bodyTemperatureDTO.setTemperature(bodyTemperature.getTemperature());
        bodyTemperatureDTO.setRequestDate(bodyTemperature.getRequestDate());
        bodyTemperatureDTO.setRequestTime(bodyTemperature.getRequestTime());
        return bodyTemperatureDTO;
    }

    public static BodyTemperature toEntity (BodyTemperatureDTO bodyTemperatureDTO) {
        BodyTemperature bodyTemperature = new BodyTemperature();
        bodyTemperature.setTemperature(bodyTemperatureDTO.getTemperature());
        bodyTemperature.setRequestDate(bodyTemperatureDTO.getRequestDate());
        bodyTemperature.setRequestTime(bodyTemperatureDTO.getRequestTime());
        return bodyTemperature;
    }
}
