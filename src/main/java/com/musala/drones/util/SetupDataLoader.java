package com.musala.drones.util;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.musala.drones.persistence.entity.Drone;
import com.musala.drones.persistence.entity.Medication;
import com.musala.drones.persistence.entity.enums.DroneModel;
import com.musala.drones.persistence.entity.enums.DroneState;
import com.musala.drones.persistence.entity.enums.MedicationState;
import com.musala.drones.persistence.entity.repository.DroneRepository;
import com.musala.drones.persistence.entity.repository.MedicationRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        // Setup data and load here!
        createDrones();
        createMedications();
        alreadySetup = true;
    }

    // The following methods must be anottated as @Transactional
    @Transactional
    private void createDrones() {
        for (int i = 0; i < 10; i++) {
            Drone drone = new Drone();
            drone.setBatteryCapacity((i + 1) * 10);
            drone.setModel(DroneModel.Lightweight);
            drone.setSerialNumber("DRN_112022-0" + i);
            drone.setWeightLimit(50 * i);
            drone.setState(DroneState.IDLE);
            droneRepository.save(drone);
        }
    }

    @Transactional
    private void createMedications() {
        for (int i = 0; i < 30; i++) {
            Medication medication = new Medication();
            medication.setCode("MDC_112022_0"+i);
            medication.setName("Vitamin"+i);
            medication.setWeigth((i+1)*10);
            medication.setState(MedicationState.AVAILABLE);
            medicationRepository.save(medication);
        }
    }

}
