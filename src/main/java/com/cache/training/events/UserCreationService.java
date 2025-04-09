package com.cache.training.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCreationService {

    private final ApplicationEventPublisher publisher;

    public void createUser(final String username) {
        log.info("User {} was created!" , username);
        publisher.publishEvent(new UserCreatedEvent(this, username));
    }
}
