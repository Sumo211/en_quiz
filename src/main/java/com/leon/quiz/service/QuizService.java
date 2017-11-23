package com.leon.quiz.service;

import com.leon.quiz.model.Question;

import java.util.List;

public interface QuizService {

    List<Question> generateQuiz(int numOfQues);

}
