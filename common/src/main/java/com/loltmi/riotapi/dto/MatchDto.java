package com.loltmi.riotapi.dto;

import com.loltmi.riotapi.entity.MatchExtra;
import com.loltmi.riotapi.entity.Matches;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class MatchDto {
    private Metadata metadata;
    private Info info;

    @Data
    public class Metadata{
        private String dataVersion;
        private String matchId;
        private List<String> participants;
    }

    @Data
    public class Info{
        private String endOfGameResult;
        private Long gameCreation;
        private Long gameDuration;
        private Long gameEndTimestamp;
        private Long gameId;
        private String gameMode;
        private String gameName;
        private Long gameStartTimestamp;
        private String gameType;
        private String gameVersion;
        private Integer mapId;
        private List<ParticipantDto> participants;
        private String platformId;
        private Integer queueId;
        private List<TeamDto> teams;
        private String tournamentCode;

        @Data
        public static class ParticipantDto{
            private Integer allInPings;
            private Integer assistMePings;
            private Integer assists;
            private Integer baronKills;
            private Integer basicPings;
            private Integer bountyLevel;
            private Integer champExperience;
            private Integer champLevel;
            private Integer championId;
            private String championName;
            private Integer commandPings;
            private Integer championTransform;
            private Integer consumablesPurchased;
            private ChallengesDto challenges;
            private Integer damageDealtToBuildings;
            private Integer damageDealtToObjectives;
            private Integer damageDealtToTurrets;
            private Integer damageSelfMitigated;
            private Integer dangerPings;
            private Integer deaths;
            private Integer detectorWardsPlaced;
            private Integer doubleKills;
            private Integer dragonKills;
            private Boolean eligibleForProgression;
            private Integer enemyMissingPings;
            private Integer enemyVisionPings;
            private Boolean firstBloodAssist;
            private Boolean firstBloodKill;
            private Boolean firstTowerAssist;
            private Boolean firstTowerKill;
            private Boolean gameEndedInEarlySurrendern;
            private Boolean gameEndedInSurrender;
            private Integer holdPings;
            private Integer getBackPings;
            private Integer goldEarned;
            private Integer goldSpent;
            private String individualPosition;
            private Integer inhibitorKills;
            private Integer inhibitorTakedowns;
            private Integer inhibitorsLost;
            private Integer item0;
            private Integer item1;
            private Integer item2;
            private Integer item3;
            private Integer item4;
            private Integer item5;
            private Integer item6;
            private Integer itemsPurchased;
            private Integer killingSprees;
            private Integer kills;
            private String lane;
            private Integer largestCriticalStrike;
            private Integer largestKillingSpree;
            private Integer largestMultiKill;
            private Integer longestTimeSpentLiving;
            private Integer magicDamageDealt;
            private Integer magicDamageDealtToChampions;
            private Integer magicDamageTaken;
            private MissionsDto missions;
            private Integer neutralMinionsKilled;
            private Integer needVisionPings;
            private Integer nexusKills;
            private Integer nexusTakedowns;
            private Integer nexusLost;
            private Integer objectivesStolen;
            private Integer objectivesStolenAssists;
            private Integer onMyWayPings;
            private Integer participantId;
            private Integer playerScore0;
            private Integer playerScore1;
            private Integer playerScore2;
            private Integer playerScore3;
            private Integer playerScore4;
            private Integer playerScore5;
            private Integer playerScore6;
            private Integer playerScore7;
            private Integer playerScore8;
            private Integer playerScore9;
            private Integer playerScore10;
            private Integer playerScore11;
            private Integer pentaKills;
            //                private PerksDto perks;
            private Integer physicalDamageDealt;
            private Integer physicalDamageDealtToChampions;
            private Integer physicalDamageTaken;
            private Integer placement;
            private Integer playerAugment1;
            private Integer playerAugment2;
            private Integer playerAugment3;
            private Integer playerAugment4;
            private Integer playerSubteamId;
            private Integer pushPings;
            private Integer profileIcon;
            private String puuid;
            private Integer quadraKills;
            private String riotIdGameName;
            private String riotIdName;
            private String riotIdTagline;
            private String role;
            private Integer sightWardsBoughtInGame;
            private Integer spell1Casts;
            private Integer spell2Casts;
            private Integer spell3Casts;
            private Integer spell4Casts;
            private Integer subteamPlacement;
            private Integer summoner1Casts;
            private Integer summoner1Id;
            private Integer summoner2Casts;
            private Integer summoner2Id;
            private String summonerId;
            private Integer summonerLevel;
            private String summonerName;
            private Boolean teamEarlySurrendered;
            private Integer teamId;
            private String teamPosition;
            private Integer timeCCingOthers;
            private Integer timePlayed;
            private Integer totalAllyJungleMinionsKilled;
            private Integer totalDamageDealt;
            private Integer totalDamageDealtToChampions;
            private Integer totalDamageShieldedOnTeammates;
            private Integer totalDamageTaken;
            private Integer totalEnemyJungleMinionsKilled;
            private Integer totalHeal;
            private Integer totalHealsOnTeammates;
            private Integer totalMinionsKilled;
            private Integer totalTimeCCDealt;
            private Integer totalTimeSpentDead;
            private Integer totalUnitsHealed;
            private Integer tripleKills;
            private Integer trueDamageDealt;
            private Integer trueDamageDealtToChampions;
            private Integer trueDamageTaken;
            private Integer turretKills;
            private Integer turretTakedowns;
            private Integer turretsLost;
            private Integer unrealKills;
            private Integer visionScore;
            private Integer visionClearedPings;
            private Integer visionWardsBoughtInGame;
            private Integer wardsKilled;
            private Integer wardsPlaced;
            private Boolean win;

            @Data
            public static class ChallengesDto{
                //                    private Integer _12AssistStreakCount;
                private Integer baronBuffGoldAdvantageOverThreshold;
                private Float controlWardTimeCoverageInRiverOrEnemyHalf;
                private Integer earliestBaron;
                private Integer earliestDragonTakedown;
                private Integer earliestElderDragon;
                private Integer earlyLaningPhaseGoldExpAdvantage;
                private Integer fasterSupportQuestCompletion;
                private Integer fastestLegendary;
                private Integer hadAfkTeammate;
                private Integer highestChampionDamage;
                private Integer highestCrowdControlScore;
                private Integer highestWardKills;
                private Integer junglerKillsEarlyJungle;
                private Integer killsOnLanersEarlyJungleAsJungler;
                private Integer laningPhaseGoldExpAdvantage;
                private Integer legendaryCount;
                private Float maxCsAdvantageOnLaneOpponent;
                private Integer maxLevelLeadLaneOpponent;
                private Integer mostWardsDestroyedOneSweeper;
                private Integer mythicItemUsed;
                private Integer playedChampSelectPosition;
                private Integer soloTurretsLategame;
                private Integer takedownsFirst25Minutes;
                private Integer teleportTakedowns;
                private Integer thirdInhibitorDestroyedTime;
                private Float visionScoreAdvantageLaneOpponent;
                private Integer threeWardsOneSweeperCount;
                private Integer InfernalScalePickup;
                private Integer fistBumpParticipation;
                private Integer voidMonsterKill;
                private Integer abilityUses;
                private Integer acesBefore15Minutes;
                private Float alliedJungleMonsterKills;
                private Integer baronTakedowns;
                private Integer blastConeOppositeOpponentCount;
                private Integer bountyGold;
                private Integer buffsStolen;
                private Integer completeSupportQuestInTime;
                private Integer controlWardsPlaced;
                private Float damagePerMinute;
                private Float damageTakenOnTeamPercentage;
                private Integer dancedWithRiftHerald;
                private Integer deathsByEnemyChamps;
                private Integer dodgeSkillShotsSmallWindow;
                private Integer doubleAces;
                private List<Integer> legendaryItemUsed;
                private Integer dragonTakedowns;
                private Float effectiveHealAndShielding;
                private Integer elderDragonKillsWithOpposingSoul;
                private Integer elderDragonMultikills;
                private Integer enemyChampionImmobilizations;
                private Float enemyJungleMonsterKills;
                private Integer epicMonsterKillsNearEnemyJungler;
                private Integer epicMonsterKillsWithin30SecondsOfSpawn;
                private Integer epicMonsterSteals;
                private Integer epicMonsterStolenWithoutSmite;
                private Integer firstTurretKilled;
                private Float firstTurretKilledTime;
                private Integer flawlessAces;
                private Integer fullTeamTakedown;
                private Float gameLength;
                private Integer getTakedownsInAllLanesEarlyJungleAsLaner;
                private Float goldPerMinute;
                private Integer hadOpenNexus;
                private Integer immobilizeAndKillWithAlly;
                private Integer initialBuffCount;
                private Integer initialCrabCount;
                private Float jungleCsBefore10Minutes;
                private Integer junglerTakedownsNearDamagedEpicMonster;
                private Float kda;
                private Integer killAfterHiddenWithAlly;
                private Integer killedChampTookFullTeamDamageSurvived;
                private Integer killingSprees;
                private Float killParticipation;
                private Integer killsNearEnemyTurret;
                private Integer killsOnOtherLanesEarlyJungleAsLaner;
                private Integer killsOnRecentlyHealedByAramPack;
                private Integer killsUnderOwnTurret;
                private Integer killsWithHelpFromEpicMonster;
                private Integer knockEnemyIntoTeamAndKill;
                private Integer kTurretsDestroyedBeforePlatesFall;
                private Integer landSkillShotsEarlyGame;
                private Integer laneMinionsFirst10Minutes;
                private Integer lostAnInhibitor;
                private Integer maxKillDeficit;
                private Integer mejaisFullStackInTime;
                private Float moreEnemyJungleThanOpponent;
                private Integer multiKillOneSpell;
                private Integer multikills;
                private Integer multikillsAfterAggressiveFlash;
                private Integer multiTurretRiftHeraldCount;
                private Integer outerTurretExecutesBefore10Minutes;
                private Integer outnumberedKills;
                private Integer outnumberedNexusKill;
                private Integer perfectDragonSoulsTaken;
                private Integer perfectGame;
                private Integer pickKillWithAlly;
                private Integer poroExplosions;
                private Integer quickCleanse;
                private Integer quickFirstTurret;
                private Integer quickSoloKills;
                private Integer riftHeraldTakedowns;
                private Integer saveAllyFromDeath;
                private Integer scuttleCrabKills;
                private Float shortestTimeToAceFromFirstTakedown;
                private Integer skillshotsDodged;
                private Integer skillshotsHit;
                private Integer snowballsHit;
                private Integer soloBaronKills;
                private Integer SWARM_DefeatAatrox;
                private Integer SWARM_DefeatBriar;
                private Integer SWARM_DefeatMiniBosses;
                private Integer SWARM_EvolveWeapon;
                private Integer SWARM_Have3Passives;
                private Integer SWARM_KillEnemy;
                private Float SWARM_PickupGold;
                private Integer SWARM_ReachLevel50;
                private Integer SWARM_Survive15Min;
                private Integer SWARM_WinWith5EvolvedWeapons;
                private Integer soloKills;
                private Integer stealthWardsPlaced;
                private Integer survivedSingleDigitHpCount;
                private Integer survivedThreeImmobilizesInFight;
                private Integer takedownOnFirstTurret;
                private Integer takedowns;
                private Integer takedownsAfterGainingLevelAdvantage;
                private Integer takedownsBeforeJungleMinionSpawn;
                private Integer takedownsFirstXMinutes;
                private Integer takedownsInAlcove;
                private Integer takedownsInEnemyFountain;
                private Integer teamBaronKills;
                private Float teamDamagePercentage;
                private Integer teamElderDragonKills;
                private Integer teamRiftHeraldKills;
                private Integer tookLargeDamageSurvived;
                private Integer turretPlatesTaken;
                private Integer turretsTakenWithRiftHerald;
                private Integer turretTakedowns;
                private Integer twentyMinionsIn3SecondsCount;
                private Integer twoWardsOneSweeperCount;
                private Integer unseenRecalls;
                private Float visionScorePerMinute;
                private Integer wardsGuarded;
                private Integer wardTakedowns;
                private Integer wardTakedownsBefore20M;
            }

            @Data
            public static class MissionsDto{
                private Integer playerScore0;
                private Integer playerScore1;
                private Integer playerScore2;
                private Integer playerScore3;
                private Integer playerScore4;
                private Integer playerScore5;
                private Integer playerScore6;
                private Integer playerScore7;
                private Integer playerScore8;
                private Integer playerScore9;
                private Integer playerScore10;
                private Integer playerScore11;
            }

            @Data
            public static class PerksDto{

            }
        }

        @Data
        public static class TeamDto{
            List<BanDto> bans;
            ObjectivesDto objectives;
            Integer teamId;
            Boolean win;

            @Data
            public static class BanDto{
                private Integer championId;
                private Integer pickTurn;
            }

            @Data
            public static class ObjectivesDto{
                private ObjectiveDto baron;
                private ObjectiveDto champion;
                private ObjectiveDto dragon;
                private ObjectiveDto horde;
                private ObjectiveDto inhibitor;
                private ObjectiveDto riftHerald;
                private ObjectiveDto tower;

                @Data
                public static class ObjectiveDto{
                    private Boolean first;
                    private Integer kills;
                }
            }
        }
    }

    public static Matches toMatch(MatchDto matchDto){
        return Matches.builder()
                .id(matchDto.getMetadata().getMatchId())
                .gameCreation(matchDto.getInfo().getGameCreation())
                .gameDuration(matchDto.getInfo().getGameDuration())
                .gameStartTimestamp(matchDto.getInfo().getGameStartTimestamp())
                .gameEndTimestamp(matchDto.getInfo().getGameEndTimestamp())
                .gameMode(matchDto.getInfo().getGameMode())
                .gameVersion(matchDto.getInfo().getGameVersion())
                .mapId(matchDto.getInfo().getMapId())
                .build();
    }

    public static List<MatchExtra> toMatchExtra(MatchDto matchDto, Matches matches){
        List<MatchExtra> matchExtraList = new ArrayList<>();
        for (Info.ParticipantDto participant : matchDto.getInfo().getParticipants()) {
            matchExtraList.add(MatchExtra.builder()
                    .allInPings(participant.getAllInPings())
                    .assistMePings(participant.getAssistMePings())
                    .basicPings(participant.getBasicPings())
                    .commandPings(participant.getCommandPings())
                    .dangerPings(participant.getDangerPings())
                    .enemyMissingPings(participant.getEnemyMissingPings())
                    .enemyVisionPings(participant.getEnemyVisionPings())
                    .getBackPings(participant.getGetBackPings())
                    .holdPings(participant.getHoldPings())
                    .needVisionPings(participant.getNeedVisionPings())
                    .onMyWayPings(participant.getOnMyWayPings())
                    .pushPings(participant.getPushPings())
                    .visionClearedPings(participant.getVisionClearedPings())
                    .controlWardsPlaced(participant.getChallenges().getControlWardsPlaced())
                    .visionWardsBoughtInGame(participant.getVisionWardsBoughtInGame())
                    .wardsPlaced(participant.getWardsPlaced())
                    .firstTurretKilled(participant.getChallenges().getFirstTurretKilled())
                    .firstTurretKilledTime(participant.getChallenges().getFirstTurretKilledTime())
                    .initialCrabCount(participant.getChallenges().getInitialCrabCount())
                    .summoner1Casts(participant.getSummoner1Casts())
                    .summoner1Id(participant.getSummoner1Id())
                    .summoner2Casts(participant.getSummoner2Casts())
                    .summoner2Id(participant.getSummoner2Id())
                    .win(participant.getWin())
                    .matches(matches)
                    .build());
        }
        return matchExtraList;
    }
}