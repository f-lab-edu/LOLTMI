/* reader-writer-processor 참고용
package com.loltmi;

import com.loltmi.riotapi.dto.MatchDto;
import com.loltmi.riotapi.entity.MatchExtra;
import com.loltmi.riotapi.repository.MatchExtraRepository;
import com.loltmi.riotapi.repository.MatchRepository;
import jakarta.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.RequestHeadersSpec;
import org.springframework.web.util.UriBuilder;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GetMatchIdListJobConfig {

    public final String JOB_NAME = "GetMatchIdListBatch";
    private final EntityManagerFactory entityManagerFactory;
    private final MatchExtraRepository matchExtraRepository;
    private final MatchRepository matchRepository;
    private Set<String> matchIdSet = new HashSet<>();
    private static String RIOT_API_KEY = "RGAPI-527cf2a4-59c6-42cf-ba72-e0d9bf76e735";
    private static String BASE_URL = "https://asia.api.riotgames.com";
    private static String MATCH_LIST_URI = "/lol/match/v5/matches/by-puuid/{puuid}/ids";
    private static String MATCH_URI = "/lol/match/v5/matches/{matchId}";

    @Bean
    public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new JobBuilder("getMatchIdList", jobRepository)
            .start(step1(jobRepository, transactionManager))
            .next(step2(jobRepository, transactionManager))
            .incrementer(new RunIdIncrementer())
            .build();
    }

    //////////////////////// MatchIdList 받아오기
    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        System.out.println("step1 시작");
        return new StepBuilder("step1", jobRepository)
            .<String, List<String>>chunk(1, transactionManager)
            .reader(reader())
            .processor(processor())
            .writer(writer())
            .allowStartIfComplete(true)
            .build();
    }

    @Bean
    public JpaCursorItemReader<String> reader(){
        System.out.println("reader1 시작");
        JpaCursorItemReader<String> reader = new JpaCursorItemReader<>();
        reader.setName("jpaCursorItemReader");
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryString("SELECT p.puuid FROM Player p");
        return reader;
    }

    @Bean
    public ItemProcessor<String, List<String>> processor() {
        return puuid -> {
            System.out.println("processor1 시작");

            RestClient restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .build();

            List<String> matchIds = restClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path(MATCH_LIST_URI)
                    .queryParam("start", 0)
                    .queryParam("count", 20)
                    .queryParam("type", "ranked")
                    .queryParam("api_key", RIOT_API_KEY)
                    .build(puuid))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            return matchIds;
        };
    }

    @Bean
    public ItemWriter<List<String>> writer() {
        return chunk -> {
            System.out.println("writer1 시작");
            chunk.getItems().forEach(item -> item.forEach(System.out::println));
            chunk.getItems().forEach(item -> item.forEach(s -> matchIdSet.add(s)));
        };
    }

    ////////////////////////////////////Match Data 가져오기
    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        System.out.println("step2 시작");
        return new StepBuilder("step2", jobRepository)
            .<String, MatchDto>chunk(1, transactionManager)
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
        return new ListItemReader<>(matchIdSet.stream().toList());
    }

    @Bean
    public ItemProcessor<String, MatchDto> processor2(){
        return matchId -> {
            System.out.println("processor2 시작");
            RestClient restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .build();

            MatchDto matchDto = restClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path(MATCH_URI)
                    .queryParam("api_key", RIOT_API_KEY)
                    .build(matchId))
                .retrieve()
                .body(MatchDto.class);

            return matchDto;
        };
    }
    @Bean
    public ItemWriter<MatchDto> writer2() {
        return chunk -> {
            System.out.println("writer2 시작");
            chunk.getItems().forEach(item -> matchExtraRepository.saveAll(MatchDto.toMatchExtra(item, matchRepository.save(MatchDto.toMatch(item)))) );
        };
    }
}
*/
