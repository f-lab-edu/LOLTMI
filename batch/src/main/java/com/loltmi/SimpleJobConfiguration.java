/*
package com.loltmi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SimpleJobConfiguration {

    @Bean(name = "simpleJob1")
    public Job simpleJob1(JobRepository jobRepository, Step simpleStep1) {
        log.info(">>> simpleJob1");
        return new JobBuilder("simpleJob1", jobRepository)
            .start(simpleStep1)
            .build();
    }
    @Bean("simpleStep1")
    public Step simpleStep1(JobRepository jobRepository, Tasklet testTasklet, PlatformTransactionManager platformTransactionManager){
        log.info(">>> simpleStep1");
        return new StepBuilder("simpleStep1", jobRepository)
            .tasklet(testTasklet, platformTransactionManager).build();
    }
    @Bean
    public Tasklet testTasklet(){
        return ((contribution, chunkContext) -> {
            log.info(">>>>> Tasklet");
            return RepeatStatus.FINISHED;
        });
    }
}*/
