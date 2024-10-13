package com.loltmi.riotapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private Integer profileIconId;
    private Integer profileIconTotalUsed;

    public static ProfileDto of(Integer key, Integer value){
        return ProfileDto.builder()
            .profileIconId(key)
            .profileIconTotalUsed(value)
            .build();
    }
}
