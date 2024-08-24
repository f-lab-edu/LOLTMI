package com.loltmi.server;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
    @Override
    Optional<TestEntity> findById(Long aLong);
}
