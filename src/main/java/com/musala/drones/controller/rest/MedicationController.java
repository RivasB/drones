package com.musala.drones.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/medication")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Unexpected error") })
public class MedicationController {

        
}
