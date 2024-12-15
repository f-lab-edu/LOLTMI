package com.loltmi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loltmi.riotapi.dto.PingDto;
import com.loltmi.riotapi.dto.ProfileDto;
import com.loltmi.riotapi.entity.Statistics;
import com.loltmi.riotapi.repository.MatchExtraRepository;
import com.loltmi.riotapi.repository.StatisticsRepository;
import com.loltmi.riotapi.service.MatchExtraService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StatisticsScheduler {

    private final MatchExtraRepository matchExtraRepository;
    private final StatisticsRepository statisticsRepository;
    private final MatchExtraService matchExtraService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(cron = "* */5 * * * *")
    public void avgPingsUsed() throws JsonProcessingException {
        log.info("avgPingsUsed() 실행");
        PingDto avgPingUsed = matchExtraRepository.avgPingUsed();

        String avgPingUsedJson = objectMapper.writeValueAsString(avgPingUsed);

        Optional<Statistics> findByName = statisticsRepository.findByName("avgPingUsed");
        if(findByName.isEmpty()) {
            Statistics statistics = Statistics.builder()
                .name("avgPingUsed")
                .contents(avgPingUsedJson)
                .build();
            statisticsRepository.save(statistics);
        }
        else {
            findByName.get().changeContents(avgPingUsedJson);
        }
    }

    @Scheduled(cron = "* */5 * * * *")
    public void winRateFirstTurretKill() throws JsonProcessingException {
        Double winRateFirstTurretKill = matchExtraRepository.winRateFirstTurretKill();

        String winRateFirstTurretKillJson = objectMapper.writeValueAsString(winRateFirstTurretKill);

        Optional<Statistics> findByName = statisticsRepository.findByName("winRateFirstTurretKill");
        if(findByName.isEmpty()) {
            Statistics statistics = Statistics.builder()
                .name("winRateFirstTurretKill")
                .contents(winRateFirstTurretKillJson)
                .build();
            statisticsRepository.save(statistics);
        }
        else {
            findByName.get().changeContents(winRateFirstTurretKillJson);
        }
    }

    @Scheduled(cron = "* */5 * * * *")
    public void winRateFirstTurretKillBefore14() throws JsonProcessingException {
        Double winRateFirstTurretKillBefore14 = matchExtraRepository.winRateFirstTurretKillBefore14();

        String winRateFirstTurretKillBefore14Json = objectMapper.writeValueAsString(winRateFirstTurretKillBefore14);

        Optional<Statistics> findByName = statisticsRepository.findByName("winRateFirstTurretKillBefore14");
        if(findByName.isEmpty()) {
            Statistics statistics = Statistics.builder()
                .name("winRateFirstTurretKillBefore14")
                .contents(winRateFirstTurretKillBefore14Json)
                .build();
            statisticsRepository.save(statistics);
        }
        else {
            findByName.get().changeContents(winRateFirstTurretKillBefore14Json);
        }
    }

    @Scheduled(cron = "* */5 * * * *")
    public void getMostUsedProfileIcon() throws JsonProcessingException {
        List<ProfileDto> mostUsedProfileIcon = matchExtraService.getMostUsedProfileIcon().stream().limit(20L).toList();
        String mostUsedProfileIconJson = objectMapper.writeValueAsString(mostUsedProfileIcon);

        Optional<Statistics> findByName = statisticsRepository.findByName("mostUsedProfileIcon");
        if(findByName.isEmpty()) {
            Statistics statistics = Statistics.builder()
                .name("mostUsedProfileIcon")
                .contents(mostUsedProfileIconJson)
                .build();
            statisticsRepository.save(statistics);
        }
        else {
            findByName.get().changeContents(mostUsedProfileIconJson);
        }
    }

    @Scheduled(cron = "* */5 * * * *")
    public void winRateTwoCrabKill() throws JsonProcessingException {
        Double winRateTwoCrabKill = matchExtraRepository.winRateTwoCrabKill();

        String winRateTwoCrabKillJson = objectMapper.writeValueAsString(winRateTwoCrabKill);

        Optional<Statistics> findByName = statisticsRepository.findByName("winRateTwoCrabKill");
        if(findByName.isEmpty()) {
            Statistics statistics = Statistics.builder()
                .name("winRateTwoCrabKill")
                .contents(winRateTwoCrabKillJson)
                .build();
            statisticsRepository.save(statistics);
        }
        else {
            findByName.get().changeContents(winRateTwoCrabKillJson);
        }
    }

}
