package com.sentinel.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Single Purpose: The entry point to bootstrap the SentinelStream Engine.
 */
@SpringBootApplication
public class SentinelStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelStreamApplication.class, args);
        System.out.println("🚀 SentinelStream Engine is LIVE!");
    }
}