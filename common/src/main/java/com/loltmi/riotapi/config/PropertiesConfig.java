package com.loltmi.riotapi.config;

import com.loltmi.riotapi.property.RiotApiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = RiotApiProperties.class)
public class PropertiesConfig {

}
