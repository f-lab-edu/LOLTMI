package com.loltmi.riotapi.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class SummonerDto {

    private String id;
    private String puuid;
    private Integer profileIconId;
    private Long summonerLevel;
}
