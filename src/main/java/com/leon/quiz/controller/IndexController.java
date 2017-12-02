package com.leon.quiz.controller;

import com.leon.quiz.dto.AnswerDTO;
import com.leon.quiz.dto.QuestionDTO;
import com.leon.quiz.dto.Quiz;
import com.leon.quiz.model.Answer;
import com.leon.quiz.model.Question;
import com.leon.quiz.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
public class IndexController {

    private final QuizService quizService;

    public IndexController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("test")
    public Quiz test(HttpSession session) throws IOException {
        List<Question> questions = quizService.generateQuiz(10);
        Map<String, List<String>> result = new HashMap<>();
        List<QuestionDTO> questionDTOs = new ArrayList<>();

        QuestionDTO questionDTO;
        for (Question question : questions) {
            questionDTO = new QuestionDTO(question.getType().toString().toLowerCase(), String.valueOf(question.getId()), question.getContent());
            List<Answer> answers = question.getAnswers();
            List<AnswerDTO> answerDTOs = new ArrayList<>();
            List<String> rightAnswers = new ArrayList<>();

            AnswerDTO answerDTO;
            for (Answer answer : answers) {
                answerDTO = new AnswerDTO(String.valueOf(answer.getId()), answer.getContent());
                answerDTOs.add(answerDTO);

                if (answer.isCorrect()) {
                    rightAnswers.add(answer.getId().toString());
                }
            }
            questionDTO.setChoices(answerDTOs);
            questionDTOs.add(questionDTO);
            result.put(question.getId().toString(), rightAnswers);
        }

        session.setAttribute("quizResult", result);
        Quiz.Page page = new Quiz.Page(questionDTOs);
        return new Quiz("American History", 300, Collections.singletonList(page));
    }

}
