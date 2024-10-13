package com.loltmi.riotapi.repository;

import com.loltmi.riotapi.entity.Matches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchRepository extends JpaRepository<Matches, String> {

    @Query(value = """
		select 
        	case when exists(
            	select 1 
                from matches m
                where m.id = :id
            )
            then 'true'
            else 'false'
            end
        """,
        nativeQuery = true
    )
    boolean existsById(@Param("id") String id);
}
