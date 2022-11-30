package com.musala.drones.mapper;

import org.springframework.stereotype.Component;

import com.musala.drones.persistence.entity.Drone;
import com.musala.drones.persistence.entity.dto.DroneOutputDTO;

@Component
public class DroneEntityToDroneDTO implements IMapper<Drone, DroneOutputDTO> {

    @Override
    public DroneOutputDTO map(Drone in) {
        DroneOutputDTO droneOutupDTO = new DroneOutputDTO();
        droneOutupDTO.setId(in.getId());
        droneOutupDTO.setSerialNumber(in.getSerialNumber());
        droneOutupDTO.setModel(in.getModel());
        droneOutupDTO.setState(in.getState());
        droneOutupDTO.setWeightLimit(in.getWeightLimit());
        droneOutupDTO.setPayload(in.getPayload());
        droneOutupDTO.setPayloadWeigth(in.getPayloadWeigth());
        droneOutupDTO.setBatteryCapacity(in.getBatteryCapacity());
        return droneOutupDTO;
    }
    
}
