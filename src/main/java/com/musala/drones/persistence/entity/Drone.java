package com.musala.drones.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;

import com.musala.drones.exceptions.DroneControlException;
import com.musala.drones.persistence.entity.enums.DroneModel;
import com.musala.drones.persistence.entity.enums.DroneState;

import lombok.Data;

@Entity
@Data
public class Drone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "serial_number", unique = true, length = 100, nullable = false)
    private String serialNumber;

    @Column(columnDefinition = "VARCHAR(16)")
    private DroneModel model;

    @Column(name = "weigth_limit")
    @Max(value = 500L, message = "The max value of weigth limit is 500g")
    private int weightLimit;

    @Column(name = "battery_capacity")
    @Max(value = 100L, message = "Battery Capacity is a percentage value and can not be greater than 100")
    private int batteryCapacity;

    @Column(columnDefinition = "VARCHAR(16)")
    private DroneState state;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Medication> payload;

    // Empty constructor
    public Drone() {
        payloadInitlzr();
    }

    // Drone function to create his own empty payload
    private void payloadInitlzr() {
        if (this.getPayload() == null) {
            List<Medication> payload = new ArrayList<>();
            this.setPayload(payload);
        }
    }

    // Setter that prevent
    // the drone from being in LOADING state if the battery level is below 25%
    public void setState(DroneState state) {
        if (this.getBatteryCapacity() <= 25 && state.compareTo(DroneState.LOADING) == 0) {
            throw new DroneControlException("The battery is under 25%", null);
        }
        this.state = state;
    }
}
