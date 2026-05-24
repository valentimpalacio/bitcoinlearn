package com.bitcoinlearn.repository;

import com.bitcoinlearn.model.QuizQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface QuizQuestionRepository extends MongoRepository<QuizQuestion, String> {
    List<QuizQuestion> findByDifficulty(String difficulty);
    List<QuizQuestion> findByCategory(String category);
    List<QuizQuestion> findByDifficultyAndCategory(String difficulty, String category);
}
