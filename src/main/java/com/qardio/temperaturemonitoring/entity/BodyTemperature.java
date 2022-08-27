package com.qardio.temperaturemonitoring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "body_temperature")
public class BodyTemperature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Double temperature;

    @Column
    private LocalDate requestDate;

    @Column
    private LocalTime requestTime;

}
