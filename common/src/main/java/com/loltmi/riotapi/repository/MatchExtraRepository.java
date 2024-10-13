package com.loltmi.riotapi.repository;

import com.loltmi.riotapi.entity.MatchExtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatchExtraRepository extends JpaRepository<MatchExtra, Long>, MatchExtraCustomRepository {

}
