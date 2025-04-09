package com.cache.training.example1;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CountryService {

    private Map<Integer, String> pieceOfPaperCache = new HashMap<>();

    //Visazinis Vladas
    private List<Country> fakeDatabase = List.of(
            new Country(1, "Lietuva", "Vilnius"),
            new Country(2, "Latvija", "Riga"),
            new Country(3, "Estija", "Talinas")
    );

    public void getCapitalOfCountry(Integer countryId) {
        //jeigu ant lapelio yra parasyta norima sostine
        if (pieceOfPaperCache.containsKey(countryId)) {
            System.out.println("Capital name by given ID from PAPER (CACHE) is: " + pieceOfPaperCache.get(countryId));
            return;
        }

        //jeigu ant lapelio NERA parasyta norima sostine
        String capital = fakeDatabase.get(countryId).getCapital();
        System.out.println("Capital name by given ID from DATABASE is: " + capital);
        pieceOfPaperCache.put(countryId, capital);
    }
}