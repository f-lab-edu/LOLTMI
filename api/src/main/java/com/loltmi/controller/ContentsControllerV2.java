package com.loltmi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contents/v2")
public class ContentsControllerV2 {
    private final StatisticsRepository statisticsRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/pings")
    public PingDto getAvgPingUsed() throws JsonProcessingException {
        Statistics findAvgPingUsed = statisticsRepository.findByName("avgPingUsed")
            .orElseThrow(RuntimeException::new);
        String avgPingUsed = findAvgPingUsed.getContents();
        return objectMapper.readValue(avgPingUsed, PingDto.class);
    }

    @GetMapping("/firstTowerKill")
    public Double getWinRateFirstTurretKill() throws JsonProcessingException {
        Statistics findWinRateFirstTurretKill = statisticsRepository.findByName(
            "winRateFirstTurretKill").orElseThrow(RuntimeException::new);
        String winRateFirstTurretKill = findWinRateFirstTurretKill.getContents();
        return objectMapper.readValue(winRateFirstTurretKill, Double.class);
    }

    @GetMapping("/firstTowerKill14")
    public Double getWinRateFirstTurretKill14() throws JsonProcessingException {
        Statistics findWinRateFirstTurretKillBefore14 = statisticsRepository.findByName(
            "winRateFirstTurretKillBefore14").orElseThrow(RuntimeException::new);
        String winRateFirstTurretKillBefore14 = findWinRateFirstTurretKillBefore14.getContents();
        return objectMapper.readValue(winRateFirstTurretKillBefore14, Double.class);
    }

    @GetMapping("/profileIcon")
    public List<ProfileDto> getMostUsedProfileIcon() throws JsonProcessingException {
        Statistics findMostUsedProfileIcon = statisticsRepository.findByName(
            "mostUsedProfileIcon").orElseThrow(RuntimeException::new);
        String mostUsedProfileIcon = findMostUsedProfileIcon.getContents();
        return objectMapper.readValue(mostUsedProfileIcon, new TypeReference<>() {});
    }

    @GetMapping("/crabKill")
    public Double getWinRateTwoCrabKill() throws JsonProcessingException {
        Statistics findWinRateTwoCrabKill = statisticsRepository.findByName(
            "winRateTwoCrabKill").orElseThrow(RuntimeException::new);
        String winRateTwoCrabKill = findWinRateTwoCrabKill.getContents();
        return objectMapper.readValue(winRateTwoCrabKill, Double.class);
    }
}
