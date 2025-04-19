package com.loltmi.controller;

import com.loltmi.riotapi.dto.CrabDto;
import com.loltmi.riotapi.dto.FirstTurretKillDto;
import com.loltmi.riotapi.dto.PingDto;
import com.loltmi.riotapi.dto.ProfileDto;
import com.loltmi.riotapi.repository.MatchExtraRepository;
import com.loltmi.riotapi.service.MatchExtraService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contents/v1")
public class ContentsControllerV1 {
    private final MatchExtraRepository matchExtraRepository;
    private final MatchExtraService matchExtraService;

    @GetMapping("/pings")
    public PingDto getAvgPingUsed(){
        return matchExtraRepository.avgPingUsed();
    }

    @GetMapping("/firstTowerKill")
    public FirstTurretKillDto getWinRateFirstTurretKill(){
        return matchExtraRepository.winRateFirstTurretKill();
    }

    @GetMapping("/profileIcon")
    public List<ProfileDto> getMostUsedProfileIcon(){
        return matchExtraService.getMostUsedProfileIcon();
    }

    @GetMapping("/crabKill")
    public CrabDto getWinRateTwoCrabKill(){
        return matchExtraRepository.winRateTwoCrabKill();
    }
}
