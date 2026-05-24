package com.bitcoinlearn.controller;

import com.bitcoinlearn.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public String quizPage(Model model,
                          @RequestParam(required = false) String difficulty,
                          @RequestParam(required = false) String category) {
        model.addAttribute("categories", quizService.getCategories());
        model.addAttribute("questions", quizService.getQuestions(difficulty, category));
        model.addAttribute("selectedDifficulty", difficulty);
        model.addAttribute("selectedCategory", category);
        return "quiz";
    }
}
