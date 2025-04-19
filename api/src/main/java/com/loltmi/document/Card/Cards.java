package com.loltmi.document.Card;

import jakarta.persistence.Id;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cards")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Cards extends BaseDocument{

    @Id
    private String id;
    private String name;
    private int hits;
    private List<Contents> contents;
    private String unit;

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class Contents{
        private String title;
        private String name;
        private String imgUrl;
        private double value;

        public void changeValue(double value){
            this.value = value;
        }
    }


}

