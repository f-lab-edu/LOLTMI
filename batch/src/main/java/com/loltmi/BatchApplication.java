/*package com.loltmi;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class BatchApplication {

    private final TestBean testBean;
    @PostConstruct
    public void dependencyTest(){
        testBean.dependencyTest();
    }
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}*/
