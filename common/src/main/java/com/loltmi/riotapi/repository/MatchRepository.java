package com.loltmi.riotapi.repository;

import com.loltmi.riotapi.entity.Matches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Matches, String> {
}
