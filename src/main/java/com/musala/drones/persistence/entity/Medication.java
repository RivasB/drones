package com.musala.drones.persistence.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.musala.drones.persistence.entity.enums.MedicationState;

import lombok.Data;

@Entity
@Data
public class Medication implements Serializable {
    
    @Id
    @Column(columnDefinition = "BINARY(16)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    @Pattern(regexp = "^[A-Za-z0-9_-]*$", message = "Allowed only letters, numbers, dashes and underscore")
    private String name;

    @Column
    private int weigth;

    @Column
    @Pattern(regexp = "^[A-Z0-9_]*$", message = "Allowed only upper case letters, numbers and underscore")
    private String code;

    @Column(columnDefinition = "VARCHAR(16)")
    private MedicationState state;

    @JsonIgnore
    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "drone_id")
    private Drone drone;
}
