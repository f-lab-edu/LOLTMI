package com.loltmi.riotapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Player extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String puuid;
    private String summonerId;
    private int profileIconId;
    private long summonerLevel;

    private String tier;
    private String ranks;
    private Integer leaguePoints;
    private Integer wins;
    private Integer losses;

    public void changePuuid(String puuid){
        this.puuid = puuid;
    }
}
