package com.loltmi.riotapi.repository;

import com.loltmi.riotapi.dto.CrabDto;
import com.loltmi.riotapi.dto.FirstTurretKillDto;
import com.loltmi.riotapi.dto.PingDto;
import java.util.List;

public interface MatchExtraCustomRepository{
    PingDto avgPingUsed();
    FirstTurretKillDto winRateFirstTurretKill();
    List<Integer> getProfileIconids();
    CrabDto winRateTwoCrabKill();
}
