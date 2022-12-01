package com.musala.drones.controller.rest;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musala.drones.persistence.entity.dto.MedicationInputDTO;
import com.musala.drones.persistence.entity.dto.MedicationOutputDTO;
import com.musala.drones.service.MedicationService;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/medication")
@RequiredArgsConstructor
@ApiResponses(value = {
                @ApiResponse(code = 400, message = "Bad Request"),
                @ApiResponse(code = 500, message = "Unexpected error") })
public class MedicationController {

        private final MedicationService service;

        @ApiOperation(value = "Register", notes = "Registering a medication")
        @ApiResponses(value = {
                        @ApiResponse(code = 201, message = "Created") })
        @PostMapping()
        public ResponseEntity<Void> create(@RequestBody MedicationInputDTO medicationInputDTO) {
                this.service.create(medicationInputDTO);
                return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        @ApiOperation(value = "Get all", notes = "Getting medications on stock")
        @GetMapping()
        public Collection<MedicationOutputDTO> getAll() {
                return service.getAll();
        }
}
