package com.cache.training.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegistryCenterService implements ApplicationListener<UserCreatedEvent> {

    private final String name = "National Registry Center";

    public void registerEntry(String propertyName) {
        log.info("{} was registerd!", propertyName);
    }
    
    @Override
    public void onApplicationEvent(UserCreatedEvent event) {
        log.info("[RegistryCenter] : Registerin new user to our records: {}", event.getUsername());
    }
}
