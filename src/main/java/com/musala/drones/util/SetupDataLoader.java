package com.musala.drones.util;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

         if (alreadySetup) return;
        //Setup data and load here!
         alreadySetup = true;
    }

    //The following methods must be anottated as @Transactional
    
}
