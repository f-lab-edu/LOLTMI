package com.loltmi.riotapi.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "riot")
@Getter
@RequiredArgsConstructor
public class RiotApiProperties {

    private final String apiKey;
    private final UriProperties uri;

    @Getter
    @RequiredArgsConstructor
    public static class UriProperties{
        private final String baseKr;
        private final String baseAsia;
        private final String highLevelTierList;
        private final String otherTierList;
        private final String puuidBySummonerId;
        private final String matchList;
        private final String matchDetail;
    }
}
