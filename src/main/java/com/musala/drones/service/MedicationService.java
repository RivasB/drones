package com.musala.drones.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.musala.drones.exceptions.DroneControlException;
import com.musala.drones.persistence.entity.Medication;
import com.musala.drones.persistence.entity.enums.MedicationState;
import com.musala.drones.persistence.entity.repository.MedicationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicationService {
    private final MedicationRepository repository;

    // Find medication by id and returns it if exist
    public Medication findById(UUID medication_id) {
        Optional<Medication> medication = repository.findById(medication_id);
        if (medication.isEmpty()) {
            throw new DroneControlException("Medication not found", HttpStatus.NOT_FOUND);
        }
        return medication.get();
    }

    // Find medication by id, checks if it is available and returns it
    public Medication findAvailableById(UUID medication_id) {
        Medication medication = findById(medication_id);
        if (medication.getState().compareTo(MedicationState.AVAILABLE) != 0) {
            throw new DroneControlException("The requested medication is not available", HttpStatus.FORBIDDEN);
        }
        medication.setState(MedicationState.SHIPPED);
        return repository.save(medication);
    }
}
