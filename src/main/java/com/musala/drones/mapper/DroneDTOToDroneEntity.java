package com.musala.drones.mapper;

import org.springframework.stereotype.Component;

import com.musala.drones.persistence.entity.Drone;
import com.musala.drones.persistence.entity.dto.DroneInputDTO;
import com.musala.drones.persistence.entity.enums.DroneState;

@Component
public class DroneDTOToDroneEntity implements IMapper<DroneInputDTO, Drone> {

    @Override
    public Drone map(DroneInputDTO in) {
        Drone drone = new Drone();
        drone.setModel(in.getModel());
        drone.setSerialNumber(in.getSerialNumber());
        drone.setWeightLimit(in.getWeightLimit());
        drone.setBatteryCapacity(100);
        drone.setState(DroneState.IDLE);
        return drone;
    }
    
}
