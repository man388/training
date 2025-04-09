package com.cache.training.classwork.repository;

import com.cache.training.classwork.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
