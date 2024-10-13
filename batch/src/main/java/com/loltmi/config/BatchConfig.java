package com.loltmi.config;

import org.springframework.batch.core.converter.JobParametersConverter;
import org.springframework.batch.core.converter.JsonJobParametersConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

    @Bean
    public JobParametersConverter jsonJobParametersConverter() {
        return new JsonJobParametersConverter();
    }
}
