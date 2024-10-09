package com.loltmi;

import com.loltmi.riotapi.dto.MatchDto;
import com.loltmi.riotapi.property.RiotApiProperties;
import com.loltmi.riotapi.repository.MatchExtraRepository;
import com.loltmi.riotapi.repository.MatchRepository;
import jakarta.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.List;
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

    private static Set<String> matchIdset = new HashSet();
    private int i=1;

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
            .<String,List<String>>chunk(49, transactionManager)
            .reader(reader())
            .processor(processor())
            .writer(writer())
            .allowStartIfComplete(true)
            .build();
    }

    @Bean
    public JpaCursorItemReader<String> reader(){
        log.info("reader1 시작");
        JpaCursorItemReader<String> reader = new JpaCursorItemReader<>();
        reader.setName("jpaCursorItemReader");
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryString("SELECT p.puuid FROM Player p where p.tier in ('challenger', 'grandmaster', 'master') and DATE(p.createdAt) = CURDATE()");
        return reader;
    }

    @Bean
    public ItemProcessor<String, List<String>> processor() {
        return puuid -> {
            if(i%50==0) Thread.sleep(1000 * 60);
            if(i%20==0) Thread.sleep(1000);

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

            i++;

            return matchIds;
        };
    }

    @Bean
    public ItemWriter<List<String>> writer() {
        return chunk -> {
            log.info("writer1 시작");
            chunk.getItems().forEach(item -> item.forEach(this::isAlreadyExists));
        };
    }

    private void isAlreadyExists(String matchId){
        if(!matchRepository.existsById(matchId)) matchIdset.add(matchId);
    }

    ////////////////////////////////////Match Data 가져오기
    @Bean
    public Step getMatchListStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        log.info("getMatchListStep 시작");
        log.info("matchIdSet.size() = {}", matchIdset.size());
        i %= 50;
        return new StepBuilder("getMatchListStep", jobRepository)
            .<String, MatchDto>chunk(49, transactionManager)
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
        return new ListItemReader<>(matchIdset.stream().toList());
    }

    @Bean
    public ItemProcessor<String, MatchDto> processor2(){
        return matchId -> {
            log.info("i: {}", i);
            if(i%50==0) Thread.sleep(1000 * 60);
            if(i%20==0) Thread.sleep(1000);

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

            i++;

            return matchDto;
        };
    }
    @Bean
    public ItemWriter<MatchDto> writer2() {
        return chunk -> {
            System.out.println("writer2 시작");

            chunk.getItems().forEach(matchDto -> matchExtraRepository.saveAll(MatchDto.toMatchExtra(matchDto, matchRepository.save(MatchDto.toMatch(matchDto)))) );
        };
    }
}
