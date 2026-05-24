package com.bitcoinlearn.repository;

import com.bitcoinlearn.model.GlossaryTerm;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface GlossaryTermRepository extends MongoRepository<GlossaryTerm, String> {
    List<GlossaryTerm> findByCategory(String category);
}
