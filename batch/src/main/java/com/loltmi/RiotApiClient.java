package com.loltmi;

import com.loltmi.riotapi.dto.MatchDto;
import com.loltmi.riotapi.property.RiotApiProperties;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Slf4j
public class RiotApiClient {

    private final RestClient restClient;
    private final RiotApiProperties riotApiProperties;

    public RiotApiClient(RestClient.Builder builder, RiotApiProperties riotApiProperties){
        this.restClient = builder
            .baseUrl(riotApiProperties.getUri().getBaseAsia())
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultStatusHandler(HttpStatusCode::is5xxServerError, (request, response) -> {
                log.info("5xxServerError");
            })
            .build();
        this.riotApiProperties = riotApiProperties;
    }

    public List<String> getMatchIds(String puuid){
        return restClient.get()
            .uri(uriBuilder -> uriBuilder
                .path(riotApiProperties.getUri().getMatchList())
                .queryParam("start", 0)
                .queryParam("count", 20)
                .queryParam("type", "ranked")
                .queryParam("api_key", riotApiProperties.getApiKey())
                .build(puuid))
            .retrieve()
            .body(new ParameterizedTypeReference<>() {});
    }

    public MatchDto getMatchDto(String matchId){
        return restClient.get()
            .uri(uriBuilder -> uriBuilder
                .path(riotApiProperties.getUri().getMatchDetail())
                .queryParam("api_key", riotApiProperties.getApiKey())
                .build(matchId))
            .retrieve()
            .body(MatchDto.class);
    }

}
