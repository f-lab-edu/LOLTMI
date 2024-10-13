package com.loltmi;

import com.loltmi.riotapi.dto.PlayerDto;
import com.loltmi.riotapi.dto.PlayerDto.Entries;
import com.loltmi.riotapi.dto.SummonerDto;
import com.loltmi.riotapi.entity.Player;
import com.loltmi.riotapi.property.RiotApiProperties;
import com.loltmi.riotapi.repository.PlayerRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestClient;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GetUserListJobConfig {

    private final RiotApiProperties riotApiProperties;
    private final PlayerRepository playerRepository;

    @Bean
    public Job getUserListJob(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new JobBuilder("getUserList", jobRepository)
            .start(getUserListStep("tier", jobRepository, transactionManager))
            .incrementer(new RunIdIncrementer())
            .build();
    }

    ///////////// 마스터 유저 리스트 데이터베이스에 날짜별로 저장
    @Bean
    public Step getUserListStep(String tier, JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new StepBuilder("getUserListStep", jobRepository)
            .tasklet(userListTasklet(tier), transactionManager)
            .allowStartIfComplete(true)
            .build();
    }

    @Bean
    @StepScope
    public Tasklet userListTasklet(@Value("#{jobParameters[tier]}") String tier){
        return ((contribution, chunkContext) -> {

            // 티어에 따른 리스트 가져오기
            RestClient restClient = RestClient.builder()
                .baseUrl(riotApiProperties.getUri().getBaseKr())
                .build();

            PlayerDto playerDto = restClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path(riotApiProperties.getUri().getHighLevelTierList())
                    .queryParam("api_key", riotApiProperties.getApiKey())
                    .build(tier))
                .retrieve()
                .body(PlayerDto.class);

            if (playerDto == null) throw new RuntimeException("playerDto가 null입니다");

            log.info("유저수: {}", playerDto.getEntries().size());

            // 가져온 summonerId로 puuid 가져오기
            List<Player> players = new ArrayList<>();
            int size = playerDto.getEntries().size();
            for(int i=1; i<size; i++){
                if(i%50==0) Thread.sleep(1000 * 60);
                if(i%20==0) Thread.sleep(1000);

                Entries entry = playerDto.getEntries().get(i);
                SummonerDto summonerDto = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                        .path(riotApiProperties.getUri().getPuuidBySummonerId())
                        .queryParam("api_key", riotApiProperties.getApiKey())
                        .build(entry.getSummonerId()))
                    .retrieve()
                    .body(SummonerDto.class);

                if(summonerDto == null) throw new RuntimeException("summonerDto가 null입니다");
                log.info("유저 puuid: {}", summonerDto.getPuuid());

                Player player = Player.builder()
                    .puuid(summonerDto.getPuuid())
                    .summonerId(summonerDto.getId())
                    .profileIconId(summonerDto.getProfileIconId())
                    .summonerLevel(summonerDto.getSummonerLevel())
                    .tier(playerDto.getTier())
                    .ranks(entry.getRank())
                    .leaguePoints(entry.getLeaguePoints())
                    .wins(entry.getWins())
                    .losses(entry.getLosses())
                    .build();

                players.add(player);
            }

            playerRepository.saveAll(players);

            return RepeatStatus.FINISHED;
        });
    }

}
