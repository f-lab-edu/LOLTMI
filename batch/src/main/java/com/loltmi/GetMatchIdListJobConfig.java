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
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestClient;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GetMatchIdListJobConfig {

    public final String JOB_NAME = "GetMatchIdListBatch";
    private final EntityManagerFactory entityManagerFactory;
    private final MatchExtraRepository matchExtraRepository;
    private Set<String> matchIdSet = new HashSet<>();
    private static String RIOT_API_KEY = "RGAPI-a9970472-4149-4226-b189-d066f5db4ba2";
    private static String BASE_URL = "https://asia.api.riotgames.com";
    private static String MATCH_LIST_URI = "/lol/match/v5/matches/by-puuid/{puuid}/ids";
    private static String MATCH_URI = "/lol/match/v5/matches/{matchId}";

    @Bean(JOB_NAME)
    public Job job(JobRepository jobRepository, Step step1, Step step2){
        return new JobBuilder("getMatchIdList", jobRepository)
            .start(step1)
            .next(step2)
//            .incrementer(new RunIdIncrementer())
            .build();
    }

    //////////////////////// MatchIdList 받아오기
    @Bean
    public Step step1(JobRepository jobRepository, JdbcCursorItemReader<String> itemReader,
        ItemProcessor itemProcessor,
        ItemWriter itemWriter,
        PlatformTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
            .<String, List<String>>chunk(10, transactionManager)
            .reader(itemReader)
            .processor(itemProcessor)
            .writer(itemWriter)
            .build();
    }

    @Bean
    public JpaCursorItemReader<String> reader(){
        JpaCursorItemReader<String> reader = new JpaCursorItemReader<>();
        reader.setName("jpaCursorItemReader");
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryString("SELECT p.puuid FROM Player p");
        return reader;
    }

    @Bean
    public ItemProcessor<String, List<String>> processor() {
        return new ItemProcessor<String, List<String>>() {
            @Override
            public List<String> process(String puuid) throws Exception {
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
            }
        };
    }

    @Bean
    public ItemWriter<List<String>> writer() {
        return new ItemWriter<List<String>>() {
            @Override
            public void write(Chunk<? extends List<String>> chunk) throws Exception {
                chunk.getItems().forEach(item -> item.forEach(s -> matchIdSet.add(s)));
            }
        };
    }

    ////////////////////////////////////Match Data 가져오기
    @Bean
    public Step step2(JobRepository jobRepository, JdbcCursorItemReader<String> itemReader,
        ItemProcessor itemProcessor,
        ItemWriter itemWriter,
        PlatformTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
            .<String, MatchDto>chunk(10, transactionManager)
            .reader(itemReader)
            .processor(itemProcessor)
            .writer(itemWriter)
            .build();
    }

    public ItemReader<String> reader2(){
        return new ItemReader<String>() {
            @Override
            public String read()
                throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                // matchIdSet 하나씩 읽기
                return null;
            }
        };
    }

    public ItemProcessor<String, MatchDto> processor2(){
        return new ItemProcessor<String, MatchDto>() {
            @Override
            public MatchDto process(String matchId) throws Exception {
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
            }
        };
    }
    @Bean
    public ItemWriter<MatchDto> writer2() {
        return new ItemWriter<MatchDto>() {
            @Override
            public void write(Chunk<? extends MatchDto> chunk) throws Exception {
                chunk.getItems().forEach(item -> matchExtraRepository.saveAll(MatchDto.toMatchExtra(item)) );
            }
        };
    }
}
