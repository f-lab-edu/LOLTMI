package com.loltmi.riotapi.repository;

import com.loltmi.riotapi.entity.Player;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("select p.puuid from Player p")
    List<String> findPuuids();
}
