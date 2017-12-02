package com.leon.quiz.controller;

import com.leon.quiz.dto.FinalResultDTO;
import com.leon.quiz.dto.QuizResultDTO;
import com.leon.quiz.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/start-quiz")
    public String showStartQuiz() {
        return "start-quiz";
    }

    @PostMapping("/quiz-result")
    @ResponseBody
    public FinalResultDTO postQuizResult(@RequestBody QuizResultDTO result, HttpSession session) {
        Map<String, List<String>> quizResult = (Map<String, List<String>>) session.getAttribute("quizResult");
        FinalResultDTO finalResultDTO = new FinalResultDTO();
        finalResultDTO.setUserId(result.getUserId());
        finalResultDTO.setNumOfRightAnswers(quizService.markQuiz(result, quizResult));
        finalResultDTO.setTotalQuestions(quizResult.size());
        session.removeAttribute("quizResult");
        return finalResultDTO;
    }

}
