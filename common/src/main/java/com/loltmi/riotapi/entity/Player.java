package com.loltmi.riotapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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
    @ColumnDefault("-1")
    private int leaguePoints;
    @ColumnDefault("-1")
    private int wins;
    @ColumnDefault("-1")
    private int losses;

    public void changePuuid(String puuid){
        this.puuid = puuid;
    }
}
