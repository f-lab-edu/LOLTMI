package com.loltmi.riotapi.entity;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class MatchExtra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer allInPings;
    private Integer assistMePings;
    private Integer basicPings;
    private Integer commandPings;
    private Integer dangerPings;
    private Integer enemyMissingPings;
    private Integer enemyVisionPings;
    private Integer getBackPings;
    private Integer holdPings;
    private Integer needVisionPings;
    private Integer onMyWayPings;
    private Integer pushPings;
    private Integer visionClearedPings;

    private Integer controlWardsPlaced;

    private Integer visionWardsBoughtInGame;
    private Integer wardsPlaced;

    private Integer firstTurretKilled;
    private Float firstTurretKilledTime;

    private Integer initialCrabCount;

    private Integer summoner1Casts;
    private Integer summoner1Id;
    private Integer summoner2Casts;
    private Integer summoner2Id;

    private Boolean win;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matches_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Matches matches;

}
