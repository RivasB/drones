package com.musala.drones.persistence.entity.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class MedicationOutputDTO {
    private UUID id;
    private String name;
    private int weigth;
    private String code;
    private MedicationState state;
}
