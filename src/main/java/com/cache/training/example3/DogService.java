package com.cache.training.example3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class DogService {

    private final DogRepository dogRepository;
    //private Map<Integer, String> inMemoryCache = new HashMap<>(); //primitive approach
    private final CacheManager cacheManager; //Spring provided instance of cache.

    public Dog getDogById(final Long dogId) {
        Cache dogCache = cacheManager.getCache("dogs"); //getCache jei neranda tokio cacho, tai cache bus Null. jei norim kad rastu - reikia apsirasyti cacheConfig.

        if (dogCache != null) {
            Dog dogFromCache = dogCache.get(dogId, Dog.class);
            if (dogFromCache != null) {
                log.info("Retrieved dog from cache {}", dogFromCache);
                return dogFromCache;
            }
        }

        log.info("Retrieving dog from H2 database..............");
        Dog dogFromDB = dogRepository.findById(dogId).orElse(null);

        if (dogFromDB != null && dogCache != null) {
            dogCache.put(dogId, dogFromDB);
            log.info("Putting dog to the dog {} cache!", dogFromDB);
        }

        return dogFromDB;

    }

    public void clearCache(String cacheNameToClear) {
        Cache cacheToClear = cacheManager.getCache(cacheNameToClear);
        if (cacheToClear != null) {
            cacheToClear.clear();
            log.warn("{} was cleared!", cacheNameToClear);
        }
    }

}
