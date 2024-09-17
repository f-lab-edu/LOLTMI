package com.loltmi.riotapi.entity;

import jakarta.persistence.Entity;
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
public class Player {
    @Id
    private String puuid;
    private String gameName;
    private String tagLine;
    private String playerName;
    private String playerTeam;

    public void changePuuid(String puuid){
        this.puuid = puuid;
    }
}
