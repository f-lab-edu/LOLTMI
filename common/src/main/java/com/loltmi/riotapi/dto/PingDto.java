package com.loltmi.riotapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PingDto {

    private Double allInPings;
    private Double assistMePings;
    private Double commandPings;
    private Double enemyMissingPings;
    private Double enemyVisionPings;
    private Double getBackPings;
    private Double needVisionPings;
    private Double onMyWayPings;

}