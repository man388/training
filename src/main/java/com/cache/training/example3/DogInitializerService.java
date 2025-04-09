package com.cache.training.example3;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogInitializerService {

    @Autowired
    private DogRepository dogRepository;
    @Autowired
    private DogService dogService;

    @PostConstruct
    public void setup() {
        dogRepository.save(new Dog(null, "Fido"));

        dogService.getDogById(1L);
        dogService.getDogById(1L);
        dogService.clearCache("dogs");
        dogService.getDogById(1L);
    }
}