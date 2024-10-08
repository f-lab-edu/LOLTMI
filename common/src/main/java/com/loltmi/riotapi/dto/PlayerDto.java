package com.loltmi.riotapi.dto;

import java.util.List;
import lombok.Data;
import lombok.Getter;

@Getter
public class PlayerDto {
    private String tier;
    private String leagueId;
    private String queue;
    private String name;
    private List<Entries> entries;

    @Getter
    public static class Entries{
        private String summonerId;
        private Integer leaguePoints;
        private String rank;
        private Integer wins;
        private Integer losses;
    }
}
