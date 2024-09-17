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
public class Matches {

    @Id
    private String id;
    private Long gameCreation;
    private Long gameDuration;
    private Long gameStartTimestamp;
    private Long gameEndTimestamp;
    private String gameMode;
    private String gameVersion;
    private Integer mapId;
}
