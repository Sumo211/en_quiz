package com.leon.quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuizController {
    
    @GetMapping("/start-quiz")
    public String showStartQuiz() {
        return "start-quiz";
    }
}
