package com.musala.drones.controller.rest;

import java.util.Collection;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musala.drones.persistence.entity.Medication;
import com.musala.drones.persistence.entity.dto.DroneInputDTO;
import com.musala.drones.persistence.entity.dto.DroneOutputDTO;
import com.musala.drones.service.DroneService;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/drones")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Unexpected error") })
public class DroneController {
    private final DroneService service;

    @ApiOperation(value = "Register", notes = "Registering a drone")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created") })
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody DroneInputDTO droneInputDTO) {
        this.service.create(droneInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "Load", notes = "Load a drone with a medication")
    @PatchMapping("/load/{drone_id}")
    public ResponseEntity<Void> loadDrone(@PathVariable("drone_id") UUID drone_id,
            @RequestParam("medication_id") UUID medication_id) {
        service.load(drone_id, medication_id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Check payload", notes = "Get items in the payload of a given drone")
    @GetMapping("/payload/{drone_id}")
    public Collection<Medication> checkPayload(@PathVariable("drone_id") UUID drone_id) {
        return service.getDronePayloadItems(drone_id);
    }

    @ApiOperation(value = "Check drones for loading", notes = "Checking available drones for loading")
    @GetMapping("/for_loading")
    public Collection<DroneOutputDTO> getDronesForLoading() {
        return service.getDronesForLoading();
    }

    @ApiOperation(value = "Check Battery", notes = "Check drone battery level for a given drone")
    @GetMapping("/battery/{drone_id}")
    public int checkBattery(@PathVariable("drone_id") UUID drone_id) {
        return service.checkBattery(drone_id);
    }

}
