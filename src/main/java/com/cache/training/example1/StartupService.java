package com.cache.training.example1;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StartupService {



    private final CountryService countryService;

    @PostConstruct //iskviecia metoda iskarto po BEANSO sukurimo
    public void startup() {
        countryService.getCapitalOfCountry(1);
        countryService.getCapitalOfCountry(1);
        countryService.getCapitalOfCountry(1);
    }
}
