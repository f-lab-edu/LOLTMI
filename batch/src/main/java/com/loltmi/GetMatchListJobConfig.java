package com.loltmi;

import com.loltmi.riotapi.dto.MatchDto;
import com.loltmi.riotapi.entity.MatchExtra;
import com.loltmi.riotapi.entity.Matches;
import com.loltmi.riotapi.property.RiotApiProperties;
import com.loltmi.riotapi.repository.MatchExtraRepository;
import com.loltmi.riotapi.repository.MatchRepository;
import com.loltmi.riotapi.repository.TeamsRepository;
import jakarta.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestClient;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GetMatchListJobConfig {
    private final RiotApiProperties riotApiProperties;
    private final EntityManagerFactory entityManagerFactory;

    private final MatchRepository matchRepository;
    private final MatchExtraRepository matchExtraRepository;
    private final TeamsRepository teamsRepository;

    private static Set<String> matchIdset = new HashSet<>();
    private int currentRequestNum =1;

    private static final int RIOT_API_LIMIT_PER_MINUTE = 50;
    private static final int RIOT_API_LIMIT_PER_SECOND = 20;

    //////////////////////// MatchIdList 받아오기
    @Bean
    public Job getMatchJob(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new JobBuilder("getMatchJob", jobRepository)
            .start(getMatchStep(jobRepository, transactionManager))
            .next(getMatchListStep(jobRepository, transactionManager))
            .incrementer(new RunIdIncrementer())
            .build();
    }

    @Bean
    public Step getMatchStep(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        log.info("getMatchStep 시작");
        return new StepBuilder("getMatchStep", jobRepository)
            .<String,List<String>>chunk(RIOT_API_LIMIT_PER_MINUTE-1, transactionManager)
            .reader(reader(null, 0, null))
            .processor(processor())
            .writer(writer())
            .allowStartIfComplete(true)
            .build();
    }

    @Bean
    @StepScope
    public JpaCursorItemReader<String> reader(@Value("#{jobParameters[tier]}") List<String> tier, @Value("#{jobParameters[leaguePoints]}") int leaguePoints,
        @Value("#{jobParameters[curDate]}") LocalDate curDate){

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("tier", tier);
        parameterMap.put("leaguePoints", leaguePoints);
        parameterMap.put("curDate", curDate);

        log.info("reader1 시작");
        JpaCursorItemReader<String> reader = new JpaCursorItemReader<>();
        reader.setName("jpaCursorItemReader");
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryString("SELECT p.puuid FROM Player p where p.tier in (:tier) and p.leaguePoints >= :leaguePoints and p.createdAt = :curDate");
        reader.setParameterValues(parameterMap);

        return reader;
    }

    @Bean
    public ItemProcessor<String, List<String>> processor() {
        return puuid -> {
            if(currentRequestNum % RIOT_API_LIMIT_PER_MINUTE==0) Thread.sleep(1000 * 60);
            if(currentRequestNum % RIOT_API_LIMIT_PER_SECOND==0) Thread.sleep(1000);

            RestClient restClient = RestClient.builder()
                .baseUrl(riotApiProperties.getUri().getBaseAsia())
                .build();

            List<String> matchIds = restClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path(riotApiProperties.getUri().getMatchList())
                    .queryParam("start", 0)
                    .queryParam("count", 20)
                    .queryParam("type", "ranked")
                    .queryParam("api_key", riotApiProperties.getApiKey())
                    .build(puuid))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            currentRequestNum++;

            return matchIds;
        };
    }

    @Bean
    public ItemWriter<List<String>> writer() {
        return chunk -> {
            log.info("writer1 시작");
            chunk.getItems().forEach(item -> item.forEach(this::addMatchId));
        };
    }

    private void addMatchId(String matchId){
        if(!matchRepository.existsById(matchId)) matchIdset.add(matchId);
    }

    ////////////////////////////////////Match Data 가져오기
    @Bean
    public Step getMatchListStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        log.info("getMatchListStep 시작");
        return new StepBuilder("getMatchListStep", jobRepository)
            .<String, MatchDto>chunk(RIOT_API_LIMIT_PER_MINUTE-1, transactionManager)
            .reader(reader2())
            .processor(processor2())
            .writer(writer2())
            .allowStartIfComplete(true)
            .build();
    }

    @Bean
    @StepScope
    public ListItemReader<String> reader2(){
        System.out.println("reader2 시작");
        log.info("matchIdSet.size() = {}", matchIdset.size());
        return new ListItemReader<>(matchIdset.stream().toList());
    }

    @Bean
    public ItemProcessor<String, MatchDto> processor2(){
        return matchId -> {
            if(currentRequestNum % RIOT_API_LIMIT_PER_MINUTE==0) Thread.sleep(1000 * 60);
            if(currentRequestNum % RIOT_API_LIMIT_PER_SECOND==0) Thread.sleep(1000);

            RestClient restClient = RestClient.builder()
                .baseUrl(riotApiProperties.getUri().getBaseAsia())
                .build();

            MatchDto matchDto = restClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path(riotApiProperties.getUri().getMatchDetail())
                    .queryParam("api_key", riotApiProperties.getApiKey())
                    .build(matchId))
                .retrieve()
                .body(MatchDto.class);

            currentRequestNum++;

            return matchDto;
        };
    }
    @Bean
    public ItemWriter<MatchDto> writer2() {
        return chunk -> {
            System.out.println("writer2 시작");

            chunk.getItems().forEach(this::save);
        };
    }

    private void save(MatchDto matchDto) {
        Matches matches = matchRepository.save(MatchDto.toMatch(matchDto));
        matchExtraRepository.saveAll(MatchDto.toMatchExtra(matchDto, matches));
        teamsRepository.saveAll(MatchDto.toTeams(matchDto, matches));
    }
}
