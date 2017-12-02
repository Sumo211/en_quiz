package com.leon.quiz.service;

import com.leon.quiz.dto.QuizResultDTO;
import com.leon.quiz.model.Question;

import java.util.List;
import java.util.Map;

public interface QuizService {

    List<Question> generateQuiz(int numOfQues);

    int markQuiz(QuizResultDTO actual, Map<String, List<String>> expected);

}
