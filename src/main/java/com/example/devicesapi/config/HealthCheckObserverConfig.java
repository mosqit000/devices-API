package com.example.devicesapi.config;

import com.example.devicesapi.observer.HealthCheckObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthCheckObserverConfig {

    @Autowired
    HealthCheckObserver healthCheckObserver;


    @Bean
    public CommandLineRunner commandLineRunner() { return (args) -> healthCheckObserver.run(); }
}
