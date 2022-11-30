package com.musala.drones.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.musala.drones.exceptions.DroneControlException;
import com.musala.drones.mapper.DroneDTOToDroneEntity;
import com.musala.drones.mapper.DroneEntityToDroneDTO;
import com.musala.drones.persistence.entity.Drone;
import com.musala.drones.persistence.entity.Medication;
import com.musala.drones.persistence.entity.dto.DroneInputDTO;
import com.musala.drones.persistence.entity.dto.DroneOutputDTO;
import com.musala.drones.persistence.entity.enums.DroneState;
import com.musala.drones.persistence.entity.repository.DroneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DroneService {

    private final DroneRepository repository;
    private final DroneDTOToDroneEntity inputMapper;
    private final DroneEntityToDroneDTO outputMapper;
    private final MedicationService medicationService;

    // Find a drone by the given ID and returns it
    private Drone findDroneById(UUID id) {
        Optional<Drone> drone = repository.findById(id);
        if (drone.isEmpty()) {
            throw new DroneControlException("Drone not found", HttpStatus.NOT_FOUND);
        }
        return drone.get();
    }

    // Register a new drone
    public void create(DroneInputDTO droneInputDTO) {
        Drone drone = inputMapper.map(droneInputDTO);
        repository.save(drone);
    }

    // Load a medication item into the payload of a given drone
    public void load(UUID drone_id, UUID medication_id) {
        Medication medication = medicationService.findAvailableById(medication_id);
        Drone drone = findDroneById(drone_id);
        if (drone.getPayloadWeigth() + medication.getWeigth() > drone.getWeightLimit()) {
            throw new DroneControlException("Drone Overload", HttpStatus.FORBIDDEN);
        }
        drone.getPayload().add(medication);
        drone.setState(DroneState.LOADING);
        repository.save(drone);
    }

    // Returns the payload of a given drone
    public Collection<Medication> getDronePayloadItems(UUID drone_id) {
        return findDroneById(drone_id).getPayload();
    }

    // Returns a list of drones avalivable to loading
    public Collection<DroneOutputDTO> getDronesForLoading() {
        Collection<Drone> droneList = repository.getDronesForLoading();
        List<DroneOutputDTO> droneOutputDTOs = new ArrayList<>();
        droneList.stream()
                .filter(drone -> drone.getPayloadWeigth() < drone.getWeightLimit())
                .forEach(item -> droneOutputDTOs.add(outputMapper.map(item)));
        return droneOutputDTOs;
    }

    // Returns the battery capacity of a given drone
    public int checkBattery(UUID drone_id) {
        Drone drone = findDroneById(drone_id);
        return drone.getBatteryCapacity();
    }

    //Returns all drones
    public List<Drone> findAll() {
        return repository.findAll();
    }
}
