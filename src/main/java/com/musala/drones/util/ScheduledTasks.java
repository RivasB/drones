package com.musala.drones.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.musala.drones.persistence.entity.Drone;
import com.musala.drones.service.DroneService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final DroneService droneService;

    @Scheduled(fixedRate = 60000)
    public void DroneBatteryScheduleTask() {
        List<Drone> dorneList = droneService.findAll();
        dorneList.stream()
                .forEach(drone -> {
                    if (drone.getBatteryCapacity() < 25) {
                        logger.warn("Drone " + drone.getSerialNumber() + " has " + drone.getBatteryCapacity()
                                + "% of Battery, Need to charge :: Execution Time - {}",
                                dateTimeFormatter.format(LocalDateTime.now()));
                    } else {
                        logger.info("Drone " + drone.getSerialNumber() + " has " + drone.getBatteryCapacity()
                                + "% of Battery :: Execution Time - {}",
                                dateTimeFormatter.format(LocalDateTime.now()));
                    }
                });
    }
}
