package com.loltmi.riotapi.entity;

import com.fasterxml.jackson.databind.ser.Serializers.Base;
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
public class MatchExtra extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int allInPings;
    private int assistMePings;
    private int basicPings;
    private int commandPings;
    private int dangerPings;
    private int enemyMissingPings;
    private int enemyVisionPings;
    private int getBackPings;
    private int holdPings;
    private int needVisionPings;
    private int onMyWayPings;
    private int pushPings;
    private int visionClearedPings;

    private int controlWardsPlaced;

    private int visionWardsBoughtInGame;
    private int wardsPlaced;

    private int firstTurretKilled;
    private Float firstTurretKilledTime;

    private int initialCrabCount;

    private int summoner1Casts;
    private int summoner1Id;
    private int summoner2Casts;
    private int summoner2Id;

    private Boolean win;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matches_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Matches matches;

}
