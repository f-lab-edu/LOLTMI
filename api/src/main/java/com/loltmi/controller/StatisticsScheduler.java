package com.loltmi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loltmi.document.Card.CardRepository;
import com.loltmi.document.Card.Cards;
import com.loltmi.document.Card.Cards.Contents;
import com.loltmi.riotapi.dto.CrabDto;
import com.loltmi.riotapi.dto.FirstTurretKillDto;
import com.loltmi.riotapi.dto.PingDto;
import com.loltmi.riotapi.dto.ProfileDto;
import com.loltmi.riotapi.entity.Statistics;
import com.loltmi.riotapi.repository.MatchExtraRepository;
import com.loltmi.riotapi.repository.StatisticsRepository;
import com.loltmi.riotapi.service.MatchExtraService;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StatisticsScheduler {

    private final MatchExtraRepository matchExtraRepository;
    private final StatisticsRepository statisticsRepository;
    private final MatchExtraService matchExtraService;
    private final CardRepository cardRepository;
    private ObjectMapper objectMapper = new ObjectMapper();
    private final String PROFILE_ICON_URL = "https://ddragon.leagueoflegends.com/cdn/15.8.1/img/profileicon/";

    @Scheduled(fixedDelay = 1000)
    public void avgPingsUsed(){
        log.info("avgPingsUsed() 실행");
        PingDto avgPingUsed = matchExtraRepository.avgPingUsed();
        log.info("Done getting data from db");

        Optional<Cards> findCard = cardRepository.findByName("평균 핑 사용 횟수");

        if(findCard.isPresent()){
            Cards cards = findCard.get();
            List<Contents> contents = cards.getContents();
            Field[] declaredFields = avgPingUsed.getClass().getDeclaredFields();

            HashMap<String, Contents> contentMap = new HashMap<>();
            for (Contents content : contents) {
                contentMap.put(content.getName(), content);
            }

            try {

                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true);
                    Object o = declaredField.get(avgPingUsed);
                    if(contentMap.containsKey(declaredField.getName())){
                        contentMap.get(declaredField.getName()).changeValue((Double) o);
                    }else{
                        contents.add(
                            Contents.builder()
                                .name(declaredField.getName())
                                .value((Double) o)
                                .build()
                        );
                    }
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            cardRepository.save(cards);
        }

        log.info("avgPingsUsed() 종료");
    }

    @Scheduled(fixedDelay = 1000)
    public void winRateFirstTurretKill(){
        log.info("winRateFirstTurretKill() 실행");
        FirstTurretKillDto winRateFirstTurretKill = matchExtraRepository.winRateFirstTurretKill();
        log.info("Done getting data from db");

        Optional<Cards> findCard = cardRepository.findByName("첫 포탑 파괴시 승률");

        if(findCard.isPresent()){
            Cards cards = findCard.get();
            List<Contents> contents = cards.getContents();
            Field[] declaredFields = winRateFirstTurretKill.getClass().getDeclaredFields();

            HashMap<String, Contents> contentMap = new HashMap<>();
            for (Contents content : contents) {
                contentMap.put(content.getName(), content);
            }

            try {

                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true);
                    Object o = declaredField.get(winRateFirstTurretKill);
                    if(contentMap.containsKey(declaredField.getName())){
                        contentMap.get(declaredField.getName()).changeValue((Double) o);
                    }else{
                        contents.add(
                            Contents.builder()
                                .name(declaredField.getName())
                                .value((Double) o)
                                .build()
                        );
                    }
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            cardRepository.save(cards);
        }

        log.info("winRateFirstTurretKill() 종료");

    }

    @Scheduled(fixedDelay = 1000)
    public void getMostUsedProfileIcon(){
        log.info("getMostUsedProfileIcon() 실행");
        List<ProfileDto> mostUsedProfileIcon = matchExtraService.getMostUsedProfileIcon().stream().limit(20L).toList();
        log.info("Done getting data from db");

        Optional<Cards> findCard = cardRepository.findByName("가장 많이 사용한 프로필 아이콘");

        if(findCard.isPresent()){
            Cards cards = findCard.get();
            List<Contents> contents = cards.getContents();

            HashMap<String, Contents> contentMap = new HashMap<>();
            for (Contents content : contents) {
                contentMap.put(content.getName(), content);
            }

            for(ProfileDto profileDto : mostUsedProfileIcon){
                String profileIconName = profileDto.getProfileIconId().toString();
                Integer profileIconValue = profileDto.getProfileIconTotalUsed();
                if(contentMap.containsKey(profileIconName)){
                    contentMap.get(profileIconName).changeValue(profileIconValue);
                }else{
                    contents.add(
                        Contents.builder()
                            .name(profileIconName)
                            .value(profileIconValue)
                            .imgUrl(PROFILE_ICON_URL + profileDto.getProfileIconId() + ".png")
                            .build()
                    );
                }
            }

            cardRepository.save(cards);
        }

        log.info("getMostUsedProfileIcon() 종료");


    }

    @Scheduled(fixedDelay = 1000)
    public void winRateCrabKill(){
        log.info("winRateTwoCrabKill() 실행");
        CrabDto winRateCrabKill = matchExtraRepository.winRateTwoCrabKill();
        log.info("Done getting data from db");

        Optional<Cards> findCard = cardRepository.findByName("첫 바위게 먹은 개수별 승률");

        if(findCard.isPresent()){
            Cards cards = findCard.get();
            List<Contents> contents = cards.getContents();
            Field[] declaredFields = winRateCrabKill.getClass().getDeclaredFields();

            HashMap<String, Contents> contentMap = new HashMap<>();
            for (Contents content : contents) {
                contentMap.put(content.getName(), content);
            }

            try {

                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true);
                    Object o = declaredField.get(winRateCrabKill);
                    if(contentMap.containsKey(declaredField.getName())){
                        contentMap.get(declaredField.getName()).changeValue((Double) o);
                    }else{
                        contents.add(
                            Contents.builder()
                                .name(declaredField.getName())
                                .value((Double) o)
                                .build()
                        );
                    }
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            cardRepository.save(cards);
        }

        log.info("winRateTwoCrabKill() 종료");

    }

}
