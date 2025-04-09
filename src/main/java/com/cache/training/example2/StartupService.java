package com.cache.training.example2;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("CustomerStartup")
@RequiredArgsConstructor
public class StartupService {

    private final CustomerService customerService;

    @PostConstruct
    public void startup() {
        customerService.getCustomerByEmail("mantas@email.com");
        customerService.getCustomerByEmail("mantas@email.com");
        customerService.getCustomerByEmail("mantas@email.com");
        customerService.getCustomerByEmail("mantas@email.com");
        customerService.getCustomerByEmail("mantas@email.com");
    }
}
