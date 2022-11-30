package com.musala.drones.persistence.entity.dto;

import com.musala.drones.persistence.entity.enums.DroneModel;

import lombok.Data;

@Data
public class DroneInputDTO {
    private String serialNumber;
    private DroneModel model;
    private int weightLimit;
}
