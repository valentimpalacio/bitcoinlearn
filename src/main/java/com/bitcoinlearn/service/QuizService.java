package com.bitcoinlearn.service;

import com.bitcoinlearn.model.QuizQuestion;
import com.bitcoinlearn.repository.QuizQuestionRepository;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class QuizService {

    private final QuizQuestionRepository repo;

    public QuizService(QuizQuestionRepository repo) {
        this.repo = repo;
    }

    public List<QuizQuestion> getQuestions(String difficulty, String category) {
        List<QuizQuestion> questions;
        if (difficulty != null && category != null) {
            questions = repo.findByDifficultyAndCategory(difficulty, category);
        } else if (difficulty != null) {
            questions = repo.findByDifficulty(difficulty);
        } else if (category != null) {
            questions = repo.findByCategory(category);
        } else {
            questions = repo.findAll();
        }
        Collections.shuffle(questions);
        return questions.size() > 10 ? questions.subList(0, 10) : questions;
    }

    public List<String> getCategories() {
        return List.of("Fundamentos", "Blockchain", "Mercado", "Seguranca", "Historia", "Mineracao");
    }
}
