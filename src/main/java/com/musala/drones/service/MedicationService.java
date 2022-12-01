package com.musala.drones.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.musala.drones.exceptions.DroneControlException;
import com.musala.drones.mapper.MedicationDTOToMedicationEntity;
import com.musala.drones.mapper.MedicationEntityToMedicationDTO;
import com.musala.drones.persistence.entity.Medication;
import com.musala.drones.persistence.entity.dto.MedicationInputDTO;
import com.musala.drones.persistence.entity.dto.MedicationOutputDTO;
import com.musala.drones.persistence.entity.enums.MedicationState;
import com.musala.drones.persistence.entity.repository.MedicationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicationService {
    private final MedicationRepository repository;
    private final MedicationEntityToMedicationDTO outputMapper;
    private final MedicationDTOToMedicationEntity inputMapper;

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

    // Get the entire list of medications
    public Collection<MedicationOutputDTO> getAll() {
        List<MedicationOutputDTO> medicationOutputDTOs = new ArrayList<>();
        repository.findAll().stream()
                .forEach(item -> medicationOutputDTOs.add(outputMapper.map(item)));
        return medicationOutputDTOs;
    }

    // Create a medication
    public void create(MedicationInputDTO medicationInputDTO) {
        Medication medication = inputMapper.map(medicationInputDTO);
        repository.save(medication);
    }
}
