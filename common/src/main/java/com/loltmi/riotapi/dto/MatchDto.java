package com.loltmi.riotapi.dto;

import com.loltmi.riotapi.dto.MatchDto.Info.TeamDto;
import com.loltmi.riotapi.entity.MatchExtra;
import com.loltmi.riotapi.entity.Matches;
import com.loltmi.riotapi.entity.Teams;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class MatchDto {
    private Metadata metadata;
    private Info info;

    @Getter
    public class Metadata{
        private String dataVersion;
        private String matchId;
        private List<String> participants;
    }

    @Getter
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

        @Getter
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

            @Getter
            public static class ChallengesDto{
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
        }

        @Getter
        public static class TeamDto{
            List<BanDto> bans;
            ObjectivesDto objectives;
            Integer teamId;
            Boolean win;

            @Getter
            public static class BanDto{
                private Integer championId;
                private Integer pickTurn;
            }

            @Getter
            public static class ObjectivesDto{
                private ObjectiveDto baron;
                private ObjectiveDto champion;
                private ObjectiveDto dragon;
                private ObjectiveDto horde;
                private ObjectiveDto inhibitor;
                private ObjectiveDto riftHerald;
                private ObjectiveDto tower;

                @Getter
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
                .assists(participant.getAssists())
                .baronKills(participant.getBaronKills())
                .bountyLevel(participant.getBountyLevel())
                .champExperience(participant.getChampExperience())
                .champLevel(participant.getChampLevel())
                .championId(participant.getChampionId())
                .championName(participant.getChampionName())
                .championTransform(participant.getChampionTransform()) // Kayn's transfo
                .consumablesPurchased(participant.getConsumablesPurchased())
                .baronBuffGoldAdvantageOverThreshold(participant.getChallenges().getBaronBuffGoldAdvantageOverThreshold())
                .controlWardTimeCoverageInRiverOrEnemyHalf(participant.getChallenges().getControlWardTimeCoverageInRiverOrEnemyHalf())
                .earliestBaron(participant.getChallenges().getEarliestBaron())
                .earliestDragonTakedown(participant.getChallenges().getEarliestDragonTakedown())
                .earliestElderDragon(participant.getChallenges().getEarliestElderDragon())
                .earlyLaningPhaseGoldExpAdvantage(participant.getChallenges().getEarlyLaningPhaseGoldExpAdvantage())
                .fasterSupportQuestCompletion(participant.getChallenges().getFasterSupportQuestCompletion())
                .fastestLegendary(participant.getChallenges().getFastestLegendary())
                .hadAfkTeammate(participant.getChallenges().getHadAfkTeammate())
                .highestChampionDamage(participant.getChallenges().getHighestChampionDamage())
                .highestCrowdControlScore(participant.getChallenges().getHighestCrowdControlScore())
                .highestWardKills(participant.getChallenges().getHighestWardKills())
                .junglerKillsEarlyJungle(participant.getChallenges().getJunglerKillsEarlyJungle())
                .killsOnLanersEarlyJungleAsJungler(participant.getChallenges().getKillsOnLanersEarlyJungleAsJungler())
                .laningPhaseGoldExpAdvantage(participant.getChallenges().getLaningPhaseGoldExpAdvantage())
                .legendaryCount(participant.getChallenges().getLegendaryCount())
                .maxCsAdvantageOnLaneOpponent(participant.getChallenges().getMaxCsAdvantageOnLaneOpponent())
                .maxLevelLeadLaneOpponent(participant.getChallenges().getMaxLevelLeadLaneOpponent())
                .mostWardsDestroyedOneSweeper(participant.getChallenges().getMostWardsDestroyedOneSweeper())
                .mythicItemUsed(participant.getChallenges().getMythicItemUsed())
                .playedChampSelectPosition(participant.getChallenges().getPlayedChampSelectPosition())
                .soloTurretsLategame(participant.getChallenges().getSoloTurretsLategame())
                .takedownsFirst25Minutes(participant.getChallenges().getTakedownsFirst25Minutes())
                .teleportTakedowns(participant.getChallenges().getTeleportTakedowns())
                .thirdInhibitorDestroyedTime(participant.getChallenges().getThirdInhibitorDestroyedTime())
                .threeWardsOneSweeperCount(participant.getChallenges().getThreeWardsOneSweeperCount())
                .visionScoreAdvantageLaneOpponent(participant.getChallenges().getVisionScoreAdvantageLaneOpponent())
                .InfernalScalePickup(participant.getChallenges().getInfernalScalePickup())
                .fistBumpParticipation(participant.getChallenges().getFistBumpParticipation())
                .voidMonsterKill(participant.getChallenges().getVoidMonsterKill())
                .abilityUses(participant.getChallenges().getAbilityUses())
                .acesBefore15Minutes(participant.getChallenges().getAcesBefore15Minutes())
                .alliedJungleMonsterKills(participant.getChallenges().getAlliedJungleMonsterKills())
                .baronTakedowns(participant.getChallenges().getBaronTakedowns())
                .blastConeOppositeOpponentCount(participant.getChallenges().getBlastConeOppositeOpponentCount())
                .bountyGold(participant.getChallenges().getBountyGold())
                .buffsStolen(participant.getChallenges().getBuffsStolen())
                .completeSupportQuestInTime(participant.getChallenges().getCompleteSupportQuestInTime())
                .damagePerMinute(participant.getChallenges().getDamagePerMinute())
                .damageTakenOnTeamPercentage(participant.getChallenges().getDamageTakenOnTeamPercentage())
                .dancedWithRiftHerald(participant.getChallenges().getDancedWithRiftHerald())
                .deathsByEnemyChamps(participant.getChallenges().getDeathsByEnemyChamps())
                .dodgeSkillShotsSmallWindow(participant.getChallenges().getDodgeSkillShotsSmallWindow())
                .doubleAces(participant.getChallenges().getDoubleAces())
                .dragonTakedowns(participant.getChallenges().getDragonTakedowns())
                .effectiveHealAndShielding(participant.getChallenges().getEffectiveHealAndShielding())
                .elderDragonKillsWithOpposingSoul(participant.getChallenges().getElderDragonKillsWithOpposingSoul())
                .elderDragonMultikills(participant.getChallenges().getElderDragonMultikills())
                .enemyChampionImmobilizations(participant.getChallenges().getEnemyChampionImmobilizations())
                .enemyJungleMonsterKills(participant.getChallenges().getEnemyJungleMonsterKills())
                .epicMonsterKillsNearEnemyJungler(participant.getChallenges().getEpicMonsterKillsNearEnemyJungler())
                .epicMonsterKillsWithin30SecondsOfSpawn(participant.getChallenges().getEpicMonsterKillsWithin30SecondsOfSpawn())
                .epicMonsterSteals(participant.getChallenges().getEpicMonsterSteals())
                .epicMonsterStolenWithoutSmite(participant.getChallenges().getEpicMonsterStolenWithoutSmite())
                .flawlessAces(participant.getChallenges().getFlawlessAces())
                .fullTeamTakedown(participant.getChallenges().getFullTeamTakedown())
                .gameLength(participant.getChallenges().getGameLength())
                .getTakedownsInAllLanesEarlyJungleAsLaner(participant.getChallenges().getGetTakedownsInAllLanesEarlyJungleAsLaner())
                .goldPerMinute(participant.getChallenges().getGoldPerMinute())
                .hadOpenNexus(participant.getChallenges().getHadOpenNexus())
                .immobilizeAndKillWithAlly(participant.getChallenges().getImmobilizeAndKillWithAlly())
                .initialBuffCount(participant.getChallenges().getInitialBuffCount())
                .jungleCsBefore10Minutes(participant.getChallenges().getJungleCsBefore10Minutes())
                .junglerTakedownsNearDamagedEpicMonster(participant.getChallenges().getJunglerTakedownsNearDamagedEpicMonster())
                .kda(participant.getChallenges().getKda())
                .killAfterHiddenWithAlly(participant.getChallenges().getKillAfterHiddenWithAlly())
                .killedChampTookFullTeamDamageSurvived(participant.getChallenges().getKilledChampTookFullTeamDamageSurvived())
                .killingSprees(participant.getChallenges().getKillingSprees())
                .killParticipation(participant.getChallenges().getKillParticipation())
                .killsNearEnemyTurret(participant.getChallenges().getKillsNearEnemyTurret())
                .killsOnOtherLanesEarlyJungleAsLaner(participant.getChallenges().getKillsOnOtherLanesEarlyJungleAsLaner())
                .killsOnRecentlyHealedByAramPack(participant.getChallenges().getKillsOnRecentlyHealedByAramPack())
                .killsUnderOwnTurret(participant.getChallenges().getKillsUnderOwnTurret())
                .killsWithHelpFromEpicMonster(participant.getChallenges().getKillsWithHelpFromEpicMonster())
                .knockEnemyIntoTeamAndKill(participant.getChallenges().getKnockEnemyIntoTeamAndKill())
                .kTurretsDestroyedBeforePlatesFall(participant.getChallenges().getKTurretsDestroyedBeforePlatesFall())
                .landSkillShotsEarlyGame(participant.getChallenges().getLandSkillShotsEarlyGame())
                .laneMinionsFirst10Minutes(participant.getChallenges().getLaneMinionsFirst10Minutes())
                .lostAnInhibitor(participant.getChallenges().getLostAnInhibitor())
                .maxKillDeficit(participant.getChallenges().getMaxKillDeficit())
                .mejaisFullStackInTime(participant.getChallenges().getMejaisFullStackInTime())
                .moreEnemyJungleThanOpponent(participant.getChallenges().getMoreEnemyJungleThanOpponent())
                .multiKillOneSpell(participant.getChallenges().getMultiKillOneSpell())
                .multikills(participant.getChallenges().getMultikills())
                .multikillsAfterAggressiveFlash(participant.getChallenges().getMultikillsAfterAggressiveFlash())
                .multiTurretRiftHeraldCount(participant.getChallenges().getMultiTurretRiftHeraldCount())
                .outerTurretExecutesBefore10Minutes(participant.getChallenges().getOuterTurretExecutesBefore10Minutes())
                .outnumberedKills(participant.getChallenges().getOutnumberedKills())
                .outnumberedNexusKill(participant.getChallenges().getOutnumberedNexusKill())
                .perfectDragonSoulsTaken(participant.getChallenges().getPerfectDragonSoulsTaken())
                .perfectGame(participant.getChallenges().getPerfectGame())
                .pickKillWithAlly(participant.getChallenges().getPickKillWithAlly())
                .poroExplosions(participant.getChallenges().getPoroExplosions())
                .quickCleanse(participant.getChallenges().getQuickCleanse())
                .quickFirstTurret(participant.getChallenges().getQuickFirstTurret())
                .quickSoloKills(participant.getChallenges().getQuickSoloKills())
                .riftHeraldTakedowns(participant.getChallenges().getRiftHeraldTakedowns())
                .saveAllyFromDeath(participant.getChallenges().getSaveAllyFromDeath())
                .scuttleCrabKills(participant.getChallenges().getScuttleCrabKills())
                .shortestTimeToAceFromFirstTakedown(participant.getChallenges().getShortestTimeToAceFromFirstTakedown())
                .skillshotsDodged(participant.getChallenges().getSkillshotsDodged())
                .skillshotsHit(participant.getChallenges().getSkillshotsHit())
                .snowballsHit(participant.getChallenges().getSnowballsHit())
                .soloBaronKills(participant.getChallenges().getSoloBaronKills())
                .soloKills(participant.getChallenges().getSoloKills())
                .stealthWardsPlaced(participant.getChallenges().getStealthWardsPlaced())
                .survivedSingleDigitHpCount(participant.getChallenges().getSurvivedSingleDigitHpCount())
                .survivedThreeImmobilizesInFight(participant.getChallenges().getSurvivedThreeImmobilizesInFight())
                .takedownOnFirstTurret(participant.getChallenges().getTakedownOnFirstTurret())
                .takedowns(participant.getChallenges().getTakedowns())
                .takedownsAfterGainingLevelAdvantage(participant.getChallenges().getTakedownsAfterGainingLevelAdvantage())
                .takedownsBeforeJungleMinionSpawn(participant.getChallenges().getTakedownsBeforeJungleMinionSpawn())
                .takedownsFirstXMinutes(participant.getChallenges().getTakedownsFirstXMinutes())
                .takedownsInAlcove(participant.getChallenges().getTakedownsInAlcove())
                .takedownsInEnemyFountain(participant.getChallenges().getTakedownsInEnemyFountain())
                .teamBaronKills(participant.getChallenges().getTeamBaronKills())
                .teamDamagePercentage(participant.getChallenges().getTeamDamagePercentage())
                .teamElderDragonKills(participant.getChallenges().getTeamElderDragonKills())
                .teamRiftHeraldKills(participant.getChallenges().getTeamRiftHeraldKills())
                .tookLargeDamageSurvived(participant.getChallenges().getTookLargeDamageSurvived())
                .turretPlatesTaken(participant.getChallenges().getTurretPlatesTaken())
                .turretsTakenWithRiftHerald(participant.getChallenges().getTurretsTakenWithRiftHerald())
                .turretTakedowns(participant.getChallenges().getTurretTakedowns())
                .twentyMinionsIn3SecondsCount(participant.getChallenges().getTwentyMinionsIn3SecondsCount())
                .twoWardsOneSweeperCount(participant.getChallenges().getTwoWardsOneSweeperCount())
                .unseenRecalls(participant.getChallenges().getUnseenRecalls())
                .visionScorePerMinute(participant.getChallenges().getVisionScorePerMinute())
                .wardsGuarded(participant.getChallenges().getWardsGuarded())
                .wardTakedowns(participant.getChallenges().getWardTakedowns())
                .wardTakedownsBefore20M(participant.getChallenges().getWardTakedownsBefore20M())
                // challengerDto
                .damageDealtToBuildings(participant.getDamageDealtToBuildings())
                .damageDealtToObjectives(participant.getDamageDealtToObjectives())
                .damageDealtToTurrets(participant.getDamageDealtToTurrets())
                .damageSelfMitigated(participant.getDamageSelfMitigated())
                .deaths(participant.getDeaths())
                .detectorWardsPlaced(participant.getDetectorWardsPlaced())
                .doubleKills(participant.getDoubleKills())
                .dragonKills(participant.getDragonKills())
                .eligibleForProgression(participant.getEligibleForProgression())
                .firstBloodAssist(participant.getFirstBloodAssist())
                .firstBloodKill(participant.getFirstBloodKill())
                .firstTowerAssist(participant.getFirstTowerAssist())
                .firstTowerKill(participant.getFirstTowerKill())
                .gameEndedInEarlySurrender(participant.getGameEndedInSurrender())
                .gameEndedInSurrender(participant.getGameEndedInSurrender())
                .goldEarned(participant.getGoldEarned())
                .goldSpent(participant.getGoldSpent())
                .individualPosition(participant.getIndividualPosition())
                .inhibitorKills(participant.getInhibitorKills())
                .inhibitorTakedowns(participant.getInhibitorTakedowns())
                .inhibitorsLost(participant.getInhibitorsLost())
                .item0(participant.getItem0())
                .item1(participant.getItem1())
                .item2(participant.getItem2())
                .item3(participant.getItem3())
                .item4(participant.getItem4())
                .item5(participant.getItem5())
                .item6(participant.getItem6())
                .itemsPurchased(participant.getItemsPurchased())
                .kills(participant.getKills())
                .lane(participant.getLane())
                .largestCriticalStrike(participant.getLargestCriticalStrike())
                .largestKillingSpree(participant.getLargestKillingSpree())
                .largestMultiKill(participant.getLargestMultiKill())
                .longestTimeSpentLiving(participant.getLongestTimeSpentLiving())
                .magicDamageDealt(participant.getMagicDamageDealt())
                .magicDamageDealtToChampions(participant.getMagicDamageDealtToChampions())
                .magicDamageTaken(participant.getMagicDamageTaken())
                .neutralMinionsKilled(participant.getNeutralMinionsKilled())
                .nexusKills(participant.getNexusKills())
                .nexusTakedowns(participant.getNexusTakedowns())
                .nexusLost(participant.getNexusLost())
                .objectivesStolen(participant.getObjectivesStolen())
                .objectivesStolenAssists(participant.getObjectivesStolenAssists())
                .pentaKills(participant.getPentaKills())
                .physicalDamageDealt(participant.getPhysicalDamageDealt())
                .physicalDamageDealtToChampions(participant.getPhysicalDamageDealtToChampions())
                .physicalDamageTaken(participant.getPhysicalDamageTaken())
                .placement(participant.getPlacement())
                .profileIcon(participant.getProfileIcon())
                .puuid(participant.getPuuid())
                .quadraKills(participant.getQuadraKills())
                .riotIdGameName(participant.getRiotIdGameName())
                .riotIdName(participant.getRiotIdName())
                .riotIdTagline(participant.getRiotIdTagline())
                .role(participant.getRole())
                .sightWardsBoughtInGame(participant.getSightWardsBoughtInGame())
                .spell1Casts(participant.getSpell1Casts())
                .spell2Casts(participant.getSpell2Casts())
                .spell3Casts(participant.getSpell3Casts())
                .spell4Casts(participant.getSpell4Casts())
                .summonerId(participant.getSummonerId())
                .summonerLevel(participant.getSummonerLevel())
                .summonerName(participant.getSummonerName())
                .teamEarlySurrendered(participant.getTeamEarlySurrendered())
                .teamId(participant.getTeamId())
                .teamPosition(participant.getTeamPosition())
                .timeCCingOthers(participant.getTimeCCingOthers())
                .timePlayed(participant.getTimePlayed())
                .totalAllyJungleMinionsKilled(participant.getTotalAllyJungleMinionsKilled())
                .totalDamageDealt(participant.getTotalDamageDealt())
                .totalDamageDealtToChampions(participant.getTotalDamageDealtToChampions())
                .totalDamageShieldedOnTeammates(participant.getTotalDamageShieldedOnTeammates())
                .totalDamageTaken(participant.getTotalDamageTaken())
                .totalEnemyJungleMinionsKilled(participant.getTotalEnemyJungleMinionsKilled())
                .totalHealsOnTeammates(participant.getTotalHealsOnTeammates())
                .totalMinionsKilled(participant.getTotalMinionsKilled())
                .totalTimeCCDealt(participant.getTotalTimeCCDealt())
                .totalTimeSpentDead(participant.getTotalTimeSpentDead())
                .totalUnitsHealed(participant.getTotalUnitsHealed())
                .tripleKills(participant.getTripleKills())
                .trueDamageDealt(participant.getTrueDamageDealt())
                .trueDamageDealtToChampions(participant.getTrueDamageDealtToChampions())
                .trueDamageTaken(participant.getTrueDamageTaken())
                .turretKills(participant.getTurretKills())
                .turretsLost(participant.getTurretsLost())
                .unrealKills(participant.getUnrealKills())
                .visionScore(participant.getVisionScore())
                .wardsKilled(participant.getWardsKilled())
                .build());

        }
        return matchExtraList;
    }

    public static List<Teams> toTeams(MatchDto matchDto, Matches matches){
        List<Teams> teams = new ArrayList<>();
        for (TeamDto teamDto : matchDto.getInfo().getTeams()) {
            Teams team = Teams.builder()
                .bansChampionId1(teamDto.getBans().get(0).getChampionId())
                .bansChampionId2(teamDto.getBans().get(1).getChampionId())
                .bansChampionId3(teamDto.getBans().get(2).getChampionId())
                .bansChampionId4(teamDto.getBans().get(3).getChampionId())
                .bansChampionId5(teamDto.getBans().get(4).getChampionId())
                .baronFirst(teamDto.getObjectives().getBaron().getFirst())
                .baronKills(teamDto.getObjectives().getBaron().getKills())
                .championFirst(teamDto.getObjectives().getChampion().getFirst())
                .championKills(teamDto.getObjectives().getChampion().getKills())
                .dragonFirst(teamDto.getObjectives().getDragon().getFirst())
                .dragonKills(teamDto.getObjectives().getDragon().getKills())
                .hordeFirst(teamDto.getObjectives().getHorde().getFirst())
                .hordeKills(teamDto.getObjectives().getHorde().getKills())
                .inhibitorFirst(teamDto.getObjectives().getInhibitor().getFirst())
                .inhibitorKills(teamDto.getObjectives().getInhibitor().getKills())
                .riftHeraldFirst(teamDto.getObjectives().getRiftHerald().getFirst())
                .riftHeraldKills(teamDto.getObjectives().getRiftHerald().getKills())
                .towerFirst(teamDto.getObjectives().getTower().getFirst())
                .towerKills(teamDto.getObjectives().getTower().getKills())
                .teamId(teamDto.getTeamId())
                .win(teamDto.getWin())
                .matches(matches)
                .build();

            teams.add(team);
        }

        return teams;
    }
}