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
@Setter
public class RiotApiProperties {

    private String apiKey;
    private UriProperties uri;

    @Getter
    @Setter
    public static class UriProperties{
        private String baseKr;
        private String baseAsia;
        private String highLevelTierList;
        private String otherTierList;
        private String puuidBySummonerId;
        private String matchList;
        private String matchDetail;
    }
}
