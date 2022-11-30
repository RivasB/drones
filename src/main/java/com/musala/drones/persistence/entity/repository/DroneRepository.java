package com.musala.drones.persistence.entity.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.drones.persistence.entity.Drone;

@Repository
public interface DroneRepository extends JpaRepository<Drone,UUID>{
    
}
