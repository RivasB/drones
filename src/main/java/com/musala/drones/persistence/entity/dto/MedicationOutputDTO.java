package com.musala.drones.persistence.entity.dto;

import java.util.UUID;

import com.musala.drones.persistence.entity.enums.MedicationState;

import lombok.Data;

@Data
public class MedicationOutputDTO {
    private UUID id;
    private String name;
    private double weigth;
    private String code;
    private MedicationState state;
}
