package com.example.devicesapi.repository;

import com.example.devicesapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    boolean existsByUsername(String username);
}
