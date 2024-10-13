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
public class Teams extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer bansChampionId1;
    private Integer bansChampionId2;
    private Integer bansChampionId3;
    private Integer bansChampionId4;
    private Integer bansChampionId5;

    private Boolean baronFirst;
    private Integer baronKills;
    private Boolean championFirst;
    private Integer championKills;
    private Boolean dragonFirst;
    private Integer dragonKills;
    private Boolean hordeFirst;
    private Integer hordeKills;
    private Boolean inhibitorFirst;
    private Integer inhibitorKills;
    private Boolean riftHeraldFirst;
    private Integer riftHeraldKills;
    private Boolean towerFirst;
    private Integer towerKills;

    private Integer teamId;
    private Boolean win;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matches_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Matches matches;
}
