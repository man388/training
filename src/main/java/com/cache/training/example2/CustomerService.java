package com.cache.training.example2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CustomerService {

    private List<Customer> fakeDatabase = List.of(
            new Customer(1, "Eglė", "egle@email.com", 4154864),
            new Customer(2, "Mantas", "mantas@email.com", 3989654),
            new Customer(3, "Edvinas", "edvinas@email.com", 3568488)
    );

    private static final int CACHE_HIT_LIMIT = 3; // Cache hit limit to prevent excessive cache usage

    private Map<String, Integer> pieceOfPaperCache = new HashMap<>();
    private Map<String, Integer> cacheCounter = new HashMap<>();

    public void getCustomerByEmail(String email) {
        // Check if the email exists in the cache
        if (pieceOfPaperCache.containsKey(email) && cacheCounter.getOrDefault(email, 0) <= CACHE_HIT_LIMIT) {
            int hitCount = cacheCounter.get(email);
            log.info("Personal ID by Email from PAPER (CACHE) is: " + pieceOfPaperCache.get(email) + " Cache hit Counter: " + hitCount);
            cacheCounter.put(email, hitCount + 1); // Increment the hit counter for this email
            return;
        }

        // If not in cache or exceeded hit limit, fetch from the fake database
        Integer personalId = fakeDatabase.stream()
                .filter(customer -> customer.getEmail().equalsIgnoreCase(email))
                .map(Customer::getPersonalId)
                .findFirst()
                .orElse(null);

        log.info("Customer Personal ID from DATABASE is: " + personalId);

        if (personalId != null) {
            pieceOfPaperCache.put(email, personalId); // Update the cache with the new personal ID
            cacheCounter.put(email, 1); // Initialize the hit counter for the cache
        }
    }


//    public void getCustomerByEmail(String email) {
//
//        int counter = 0;
//        if (pieceOfPaperCache.containsKey(email) && counter < 3) {
//            log.info("Personal ID by Email from PAPER (CACHE) is: " + pieceOfPaperCache.get(email) + " Counter: " + counter);
//            counter++;
//            return;
//        }
//
//        Integer personalId = fakeDatabase.stream()
//                .filter(customer -> customer.getEmail().equalsIgnoreCase(email))
//                .map(Customer::getPersonalId)
//                .findFirst().orElse(null);
//        log.info("Customer Personal ID from DATABASE is: " + personalId);
//        pieceOfPaperCache.put(email, personalId);
//    }
}
