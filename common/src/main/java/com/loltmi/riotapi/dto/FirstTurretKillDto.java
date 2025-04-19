package com.loltmi.riotapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FirstTurretKillDto {
    Double winRateFirstTurretKill;
    Double winRateFirstTurretKillBefore14;
}
