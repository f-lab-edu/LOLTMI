package com.loltmi;

import com.loltmi.riotapi.dto.MatchDto;
import com.loltmi.riotapi.property.RiotApiProperties;
import com.loltmi.riotapi.repository.MatchExtraRepository;
import com.loltmi.riotapi.repository.MatchRepository;
import com.loltmi.riotapi.repository.PlayerRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class LiveDataFromRiotApi {

    private final RiotApiProperties riotApiProperties;

    private static Set<String> matchIdset = new HashSet();
    private int i = 1;

    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final MatchExtraRepository matchExtraRepository;

    public void getLiveRiotApiData() throws InterruptedException {
        while(true) {
            getMasterMatchSet();
            saveMatchExtras();
            log.info("set.clear()");
            matchIdset.clear();
        }
    }

    private void saveMatchExtras() throws InterruptedException {

        log.info("start saveMatchExtras()");
        log.info("matchIdSet.size() = {}", matchIdset.size());
        for(String matchId : matchIdset) {
            log.info("i: {}", i);
            if(i%50==0) Thread.sleep(1000 * 60);
            if(i%20==0) Thread.sleep(1000);

            RestClient restClient = RestClient.builder()
                .baseUrl(riotApiProperties.getUri().getBaseAsia())
                .build();

            MatchDto matchDto = restClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path(riotApiProperties.getUri().getMatchList())
                    .queryParam("api_key", riotApiProperties.getApiKey())
                    .build(matchId))
                .retrieve()
                .body(MatchDto.class);

            matchExtraRepository.saveAll(MatchDto.toMatchExtra(matchDto, matchRepository.save(MatchDto.toMatch(matchDto))));
            i++;
        }

        log.info("end saveMatchExtras()");
    }

    private void getMasterMatchSet() throws InterruptedException {

        log.info("start getMasterMatchSet()");
        List<String> puuids = playerRepository.findByTier("GRANDMASTER");
        List<String> puuids2 = playerRepository.findByTier("MASTER");

        puuids.addAll(puuids2);

        for (String puuid : puuids) {
            if(i%50==0) Thread.sleep(1000 * 60);
            if(i%20==0) Thread.sleep(1000);

            RestClient restClient = RestClient.builder()
                .baseUrl(riotApiProperties.getUri().getBaseAsia())
                .build();

            List<String> matchIds = restClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path(riotApiProperties.getUri().getMatchList())
                    .queryParam("start", 0)
                    .queryParam("count", 20)
                    .queryParam("type", "ranked")
                    .queryParam("api_key", riotApiProperties.getApiKey())
                    .build(puuid))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            matchIdset.addAll(matchIds.stream()
                .filter(matchId -> !matchRepository.existsById(matchId))
                .toList());
            i++;
            log.info("matchIdSet.size() = {}", matchIdset.size());
        }

        log.info("end getMasterMatchSet()");
    }

}
