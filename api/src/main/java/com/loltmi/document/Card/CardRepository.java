package com.loltmi.document.Card;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<Cards, String> {
    Optional<Cards> findByName(String name);
}
