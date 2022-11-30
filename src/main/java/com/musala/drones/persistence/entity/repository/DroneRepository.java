package com.musala.drones.persistence.entity.repository;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.musala.drones.persistence.entity.Drone;

@Repository
public interface DroneRepository extends JpaRepository<Drone, UUID> {

    @Modifying
    @Query(value = "SELECT * FROM DRONE WHERE STATE LIKE 'IDLE%' OR STATE LIKE 'LOADING%'", nativeQuery = true)
    Collection<Drone> getDronesForLoading();
}
