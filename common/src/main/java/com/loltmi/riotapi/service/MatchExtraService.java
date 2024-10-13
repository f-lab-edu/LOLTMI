package com.loltmi.riotapi.service;

import com.loltmi.riotapi.dto.ProfileDto;
import com.loltmi.riotapi.repository.MatchExtraRepository;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MatchExtraService {

    private final MatchExtraRepository matchExtraRepository;

    public List<ProfileDto> getMostUsedProfileIcon() {
        // 중복을 없앤 profileIconIds 가져오기
        List<Integer> profileIconIds = matchExtraRepository.getProfileIconids();
        Map<Integer, Integer> profileIconIdAndCountMap = new HashMap<>();

        // <profileIconIds, COUNT(profileIconIds)> 만들기
        for (Integer profileIconId : profileIconIds) {
            if(profileIconIdAndCountMap.containsKey(profileIconId))
                profileIconIdAndCountMap.put(profileIconId, profileIconIdAndCountMap.get(profileIconId)+1);
            else profileIconIdAndCountMap.put(profileIconId, 1);
        }

        // value( COUNT(profileIconIds) ) 기준으로 내림차순 정렬
        LinkedList<Entry<Integer, Integer>> entryList = new LinkedList<>(profileIconIdAndCountMap.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // DTO로 변환 후 리턴
        return entryList.stream()
            .map(entry -> ProfileDto.of(entry.getKey(), entry.getValue()))
            .toList();
    }
}
