package com.loltmi;

import com.loltmi.riotapi.dto.MatchDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

public class RiotAPI_Java {

    private static String RIOT_API_KEY = "RGAPI-fe3a8839-f860-406e-9c41-ecb33925e59e";
    private static String BASE_URL = "https://asia.api.riotgames.com";
    private static String MATCH_LIST_URI = "/lol/match/v5/matches/by-puuid/{puuid}/ids";
    private static String MATCH_URI = "/lol/match/v5/matches/{matchId}";
    private static String PUUID = "Xeve4gAoUaA8V-DQHhpTpDp2RSGbO17MUz6BKIEHl2R6pYhy_E9zo1QfDSS3CWOGWu_O7Dd77u58OQ";

    public static void main(String[] args) throws SQLException, InterruptedException {

        //puuid 받아오기
        List<String> puuidList = getPuuidList();

        //매치 리스트
        Set<String> matchIdSet = getMatchIdSet(puuidList);

        //매치 정보
        MatchDto matchDto = getMatchDto(matchIdSet);

        //매치 정보 저장
        saveMatch(matchDto);

    }

    private static void saveMatch(MatchDto matchDto) throws SQLException {
        // DB Driver
        String dbDriver = "com.mysql.cj.jdbc.Driver";

        // DB URL
        // IP:PORT/스키마
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/loltmi";

        // DB ID/PW
        String dbUser = "root";
        String dbPassword = "0000";

        Connection connection = null;

        try
        {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            System.out.println("DB Connection [성공]");
        }
        catch (SQLException e)
        {
            System.out.println("DB Connection [SQL 실패]");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("DB Connection [Class 실패]");
            e.printStackTrace();
        }

        String sql = "INSERT INTO match_extra(all_in_pings, assist_me_pings) VALUES(?, ?)";
        PreparedStatement psmt = null;
        psmt = connection.prepareStatement(sql);
        psmt.setInt(1, matchDto.getInfo().getParticipants().get(0).getAllInPings());
        psmt.setInt(2, matchDto.getInfo().getParticipants().get(0).getAssistMePings());
        int count = psmt.executeUpdate();

        psmt.close();
        connection.close();
    }

    public static MatchDto getMatchDto(Set<String> matchIdSet) {

        RestClient restClient = RestClient.builder()
            .baseUrl(BASE_URL)
            .build();

        MatchDto matchDto = restClient.get()
            .uri(uriBuilder -> uriBuilder
                .path(MATCH_URI)
                .queryParam("api_key", RIOT_API_KEY)
                .build("KR_7261136379"))
            .retrieve()
            .body(MatchDto.class);

        System.out.println(matchDto.toString());

        return matchDto;
    }

    public static Set<String> getMatchIdSet(List<String> puuidList) {
        RestClient restClient = RestClient.builder()
            .baseUrl(BASE_URL)
            .build();

        Set<String> matchIdSet = new HashSet<>();

        //50개 정도
        for (String puuid : puuidList) {
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

            for (String matchId : matchIds) {
                matchIdSet.add(matchId);
                System.out.println(matchId);
            }
        }

        return matchIdSet;
    }

    public static List<String> getPuuidList() throws SQLException, InterruptedException {
        // DB Driver
        String dbDriver = "com.mysql.cj.jdbc.Driver";

        // DB URL
        // IP:PORT/스키마
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/loltmi";

        // DB ID/PW
        String dbUser = "root";
        String dbPassword = "0000";

        Connection connection = null;

        try
        {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            System.out.println("DB Connection [성공]");
        }
        catch (SQLException e)
        {
            System.out.println("DB Connection [SQL 실패]");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("DB Connection [Class 실패]");
            e.printStackTrace();
        }

        Statement stmt = connection.createStatement();
        String sql = "select puuid from player";
        ResultSet rs = stmt.executeQuery(sql);

        List<String> puuidList = new ArrayList<>();
        while (rs.next()){
            String puuid = rs.getString(1);
            puuidList.add(puuid);
        }

        for (String s : puuidList) {
            System.out.println(s);
        }

        Thread.sleep(1000);

        rs.close();
        stmt.close();
        connection.close();

        return puuidList;
    }

}
