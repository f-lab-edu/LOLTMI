package com.loltmi;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class LiveApplication {

    private final LiveDataFromRiotApi liveDataFromRiotApi;

    @PostConstruct
    public void start() throws InterruptedException {
        liveDataFromRiotApi.getLiveRiotApiData();
    }
    public static void main(String[] args) {
        SpringApplication.run(LiveApplication.class, args);
    }
}