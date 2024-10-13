package com.loltmi.riotapi.repository;

import static com.loltmi.riotapi.entity.QMatchExtra.matchExtra;
import static com.loltmi.riotapi.entity.QMatches.matches;
import static com.loltmi.riotapi.entity.QPlayer.player;

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
    public Double winRateFirstTurretKill() {
        return jpaQueryFactory
            .select(matchExtra.win.castToNum(Integer.class).avg())
            .from(matchExtra).join(matches).on(matches.id.eq(matchExtra.matches.id))
            .where(matchExtra.firstTurretKilled.eq(1))
            .fetchOne();
    }

    @Override
    public Double winRateFirstTurretKillBefore14() {
        return jpaQueryFactory
            .select(matchExtra.win.castToNum(Integer.class).avg())
            .from(matchExtra).join(matches).on(matches.id.eq(matchExtra.matches.id))
            .where(matchExtra.firstTurretKilled.eq(1),
                matchExtra.firstTurretKilledTime.lt(840))
            .fetchOne();
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
    public Double winRateTwoCrabKill() {
        return jpaQueryFactory
            .select(matchExtra.win.castToNum(Integer.class).avg())
            .from(matchExtra)
            .where(matchExtra.initialCrabCount.eq(2))
            .fetchOne();
    }
}
