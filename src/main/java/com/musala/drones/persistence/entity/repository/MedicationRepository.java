package com.musala.drones.persistence.entity.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.drones.persistence.entity.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication,UUID>{
    
}
