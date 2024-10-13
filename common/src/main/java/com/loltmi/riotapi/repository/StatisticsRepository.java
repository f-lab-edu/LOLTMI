package com.loltmi.riotapi.repository;

import com.loltmi.riotapi.entity.Statistics;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    Optional<Statistics> findByName(String name);
}
