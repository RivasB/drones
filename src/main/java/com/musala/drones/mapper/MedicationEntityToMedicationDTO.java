package com.musala.drones.mapper;

import org.springframework.stereotype.Component;

import com.musala.drones.persistence.entity.Medication;
import com.musala.drones.persistence.entity.dto.MedicationOutputDTO;

@Component
public class MedicationEntityToMedicationDTO implements IMapper<Medication, MedicationOutputDTO>{

    @Override
    public MedicationOutputDTO map(Medication in) {
        MedicationOutputDTO medicationOutputDTO = new MedicationOutputDTO();
        medicationOutputDTO.setCode(in.getCode());
        medicationOutputDTO.setId(in.getId());
        medicationOutputDTO.setName(in.getName());
        medicationOutputDTO.setWeigth(in.getWeigth());
        medicationOutputDTO.setState(in.getState());
        return medicationOutputDTO;
    }
    
}
