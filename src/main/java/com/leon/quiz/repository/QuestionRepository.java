package com.leon.quiz.repository;

import com.leon.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    //@Query(value = "SELECT * FROM question ORDER BY rand() LIMIT :numOfQues", nativeQuery = true)
    //List<Question> generateQuiz(@Param("numOfQues") int numOfQues);

}
