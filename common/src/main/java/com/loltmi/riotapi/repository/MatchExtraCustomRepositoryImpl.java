package com.loltmi.riotapi.repository;

import static com.loltmi.riotapi.entity.QMatchExtra.matchExtra;
import static com.loltmi.riotapi.entity.QMatches.matches;
import static com.loltmi.riotapi.entity.QPlayer.player;

import com.loltmi.riotapi.dto.CrabDto;
import com.loltmi.riotapi.dto.FirstTurretKillDto;
import com.loltmi.riotapi.dto.PingDto;
import com.loltmi.riotapi.dto.ProfileDto;
import com.loltmi.riotapi.entity.QMatchExtra;
import com.loltmi.riotapi.entity.QMatches;
import com.loltmi.riotapi.entity.QPlayer;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MatchExtraCustomRepositoryImpl implements MatchExtraCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public PingDto avgPingUsed() {
        return jpaQueryFactory
            .select(Projections.fields(PingDto.class,
                matchExtra.allInPings.avg().as("allInPings"),
                matchExtra.assistMePings.avg().as("assistMePings"),
                matchExtra.commandPings.avg().as("commandPings"),
                matchExtra.enemyMissingPings.avg().as("enemyMissingPings"),
                matchExtra.enemyVisionPings.avg().as("enemyVisionPings"),
                matchExtra.getBackPings.avg().as("getBackPings"),
                matchExtra.needVisionPings.avg().as("needVisionPings"),
                matchExtra.onMyWayPings.avg().as("onMyWayPings"),
                matchExtra.pushPings.avg().as("pushPings")
                )
            )
            .from(matchExtra)
            .fetchOne();
    }

    @Override
    public FirstTurretKillDto winRateFirstTurretKill() {
        Double winRateFirstTurretKill = jpaQueryFactory
            .select(matchExtra.win.castToNum(Integer.class).avg())
            .from(matchExtra)
            .where(matchExtra.firstTurretKilled.eq(1))
            .fetchOne();

        Double winRateFirstTurretKillBefore14 = jpaQueryFactory
            .select(matchExtra.win.castToNum(Integer.class).avg())
            .from(matchExtra)
            .where(matchExtra.firstTurretKilled.eq(1),
                matchExtra.firstTurretKilledTime.lt(840))
            .fetchOne();

        return FirstTurretKillDto.builder()
            .winRateFirstTurretKill(winRateFirstTurretKill * 100)
            .winRateFirstTurretKillBefore14(winRateFirstTurretKillBefore14 * 100)
            .build();
    }

    @Override
    public List<Integer> getProfileIconids() {
        return jpaQueryFactory
            .select(player.profileIconId)
            .from(player)
            .groupBy(player.puuid, player.profileIconId)
            .fetch();
    }

    @Override
    public CrabDto winRateTwoCrabKill() {
        Double crabKill0 = jpaQueryFactory
            .select(matchExtra.win.castToNum(Integer.class).avg())
            .from(matchExtra)
            .where(matchExtra.initialCrabCount.eq(0))
            .fetchOne();

        Double crabKill1 = jpaQueryFactory
            .select(matchExtra.win.castToNum(Integer.class).avg())
            .from(matchExtra)
            .where(matchExtra.initialCrabCount.eq(1))
            .fetchOne();

        Double crabKill2 = jpaQueryFactory
            .select(matchExtra.win.castToNum(Integer.class).avg())
            .from(matchExtra)
            .where(matchExtra.initialCrabCount.eq(2))
            .fetchOne();

        return CrabDto.builder()
            .crabKill0(crabKill0 * 100)
            .crabKill1(crabKill1 * 100)
            .crabKill2(crabKill2 * 100)
            .build();
    }
}
