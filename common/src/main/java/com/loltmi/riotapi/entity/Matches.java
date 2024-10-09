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
public class Matches extends BaseEntity{

    @Id
    private String id;
    private long gameCreation;
    private long gameDuration;
    private long gameStartTimestamp;
    private long gameEndTimestamp;
    private String gameMode;
    private String gameVersion;
    private int mapId;
}
