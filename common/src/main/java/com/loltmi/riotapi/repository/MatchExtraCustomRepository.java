package com.loltmi.riotapi.repository;

import com.loltmi.riotapi.dto.PingDto;
import java.util.List;

public interface MatchExtraCustomRepository{
    PingDto avgPingUsed();
    Double winRateFirstTurretKill();
    Double winRateFirstTurretKillBefore14();
    List<Integer> getProfileIconids();
    Double winRateTwoCrabKill();
}
