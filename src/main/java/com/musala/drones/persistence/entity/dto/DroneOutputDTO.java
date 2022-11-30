package com.musala.drones.persistence.entity.dto;

import java.util.Collection;
import java.util.UUID;

import com.musala.drones.persistence.entity.Medication;
import com.musala.drones.persistence.entity.enums.DroneModel;
import com.musala.drones.persistence.entity.enums.DroneState;

import lombok.Data;

@Data
public class DroneOutputDTO {
    private UUID id;
    private String serialNumber;
    private DroneModel model;
    private double weightLimit;
    private int batteryCapacity;
    private DroneState state;
    private Collection<Medication> payload;
    private double payloadWeigth;
}
