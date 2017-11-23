package com.leon.quiz.controller;

import com.leon.quiz.dto.AnswerDTO;
import com.leon.quiz.dto.QuestionDTO;
import com.leon.quiz.dto.Quiz;
import com.leon.quiz.model.Answer;
import com.leon.quiz.model.Question;
import com.leon.quiz.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class IndexController {

    private final QuizService quizService;

    public IndexController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("test")
    public Quiz test() throws IOException {
        List<Question> questions = quizService.generateQuiz(2);
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        QuestionDTO questionDTO;
        for (Question question : questions) {
            questionDTO = new QuestionDTO(question.getType().toString().toLowerCase(), String.valueOf(question.getId()), question.getContent());
            List<Answer> answers = question.getAnswers();
            List<AnswerDTO> answerDTOs = new ArrayList<>();
            AnswerDTO answerDTO;
            for (Answer answer : answers) {
                answerDTO = new AnswerDTO(String.valueOf(answer.getId()), answer.getContent());
                answerDTOs.add(answerDTO);
            }
            questionDTO.setChoices(answerDTOs);
            questionDTOs.add(questionDTO);
        }

        Quiz.Page page = new Quiz.Page(questionDTOs);
        return new Quiz("American History", "bottom", "top", 30, Collections.singletonList(page));
    }

}
