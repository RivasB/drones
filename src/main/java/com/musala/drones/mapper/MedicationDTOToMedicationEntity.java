package com.musala.drones.mapper;

import org.springframework.stereotype.Component;

import com.musala.drones.persistence.entity.Medication;
import com.musala.drones.persistence.entity.dto.MedicationInputDTO;
import com.musala.drones.persistence.entity.enums.MedicationState;

@Component
public class MedicationDTOToMedicationEntity implements IMapper<MedicationInputDTO, Medication>{

    @Override
    public Medication map(MedicationInputDTO in) {
        Medication medication = new Medication();
        medication.setCode(in.getCode());
        medication.setName(in.getName());
        medication.setWeigth(in.getWeigth());
        medication.setState(MedicationState.AVAILABLE);
        return medication;
    }
    
}
