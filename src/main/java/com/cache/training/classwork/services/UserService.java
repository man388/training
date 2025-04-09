package com.cache.training.classwork.services;

import com.cache.training.classwork.entities.User;
import com.cache.training.classwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CacheManager cacheManager;

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        Cache userCache = cacheManager.getCache("users");
        if (userCache != null){
            User userFromCache = userCache.get(id, User.class);
            if (userFromCache != null){
                return userFromCache;
            }
        }
        User userFromDb = userRepository.findById(id).orElse(null);
        if (userFromDb != null && userCache != null){
            userCache.put(id, userFromDb);
        }

        return userFromDb;
    }
}