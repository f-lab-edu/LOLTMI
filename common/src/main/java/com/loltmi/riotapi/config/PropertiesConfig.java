package com.loltmi.riotapi.config;

import com.loltmi.riotapi.property.RiotApiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties(value = RiotApiProperties.class)
@PropertySource(ignoreResourceNotFound = true,
    value = {
        "classpath:application-common.yaml"
    }, factory = YamlPropertySourceFactory.class)
public class PropertiesConfig {

}
