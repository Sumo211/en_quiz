package com.leon.quiz.service;

import com.leon.quiz.model.Question;
import com.leon.quiz.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    private final QuestionRepository questionRepository;

    public QuizServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> generateQuiz(int numOfQues) {
        List<Question> questions = questionRepository.findAll();
        Collections.shuffle(questions);
        return questions.stream().limit(numOfQues).collect(toList());
    }

}
