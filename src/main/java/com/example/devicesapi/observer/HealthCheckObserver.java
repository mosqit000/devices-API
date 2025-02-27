package com.example.devicesapi.observer;

import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HealthCheckObserver implements Runnable{

    private final TaskScheduler taskScheduler =new ConcurrentTaskScheduler();


    @Override
    public void run() {

        // this is just a sample of observers, can be used for example with actual health checks
        taskScheduler.scheduleAtFixedRate(() -> {
            System.out.println("Health check from observer at : "+ LocalDateTime.now());
        }, Duration.ofSeconds(5));
    }
}
