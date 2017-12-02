package com.leon.quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leon.quiz.dto.QuizResultDTO;

@Controller
public class QuizController {
    
    @GetMapping("/start-quiz")
    public String showStartQuiz() {
        return "start-quiz";
    }
    
    @PostMapping("/quiz-result")
    @ResponseBody
    public String postQuizResult(@RequestBody QuizResultDTO result) {
        System.out.println("Result: "+ result);
        return "Done";
    }
}
