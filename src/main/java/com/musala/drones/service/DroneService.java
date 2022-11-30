package com.musala.drones.service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.musala.drones.exceptions.DroneControlException;
import com.musala.drones.mapper.DroneDTOToDroneEntity;
import com.musala.drones.persistence.entity.Drone;
import com.musala.drones.persistence.entity.Medication;
import com.musala.drones.persistence.entity.dto.DroneInputDTO;
import com.musala.drones.persistence.entity.repository.DroneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DroneService {

    private final DroneRepository repository;
    private final DroneDTOToDroneEntity inputMapper;
    private final MedicationService medicationService;

    private Drone findDroneById(UUID id){
        Optional<Drone> drone = repository.findById(id);
        if (drone.isEmpty()) {
            throw new DroneControlException("Drone not found", HttpStatus.NOT_FOUND);
        }
        return drone.get();
    }

    public void create(DroneInputDTO droneInputDTO) {
        Drone drone = inputMapper.map(droneInputDTO);
        repository.save(drone);
    }

    public void load(UUID drone_id, UUID medication_id) {
        Medication medication = medicationService.findAvailableById(medication_id);
        Drone drone = findDroneById(drone_id);
        int payloadWeigth = 0;
        for (Medication item : drone.getPayload()) {
            payloadWeigth=payloadWeigth+item.getWeigth();
        }
        if (payloadWeigth+medication.getWeigth()>drone.getWeightLimit()) {
            throw new DroneControlException("Drone Overload", HttpStatus.FORBIDDEN);
        }
        drone.getPayload().add(medication);
        repository.save(drone);
    }

    public Collection<Medication> getDronePayloadItems(UUID drone_id) {
        return findDroneById(drone_id).getPayload();
    }    
}
