package com.cache.training.events;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventTester {

    private final UserCreationService userCreationService;

    @PostConstruct
    public void createUser() {
        userCreationService.createUser("VladasxXxDarkDragon58");
    }
}
