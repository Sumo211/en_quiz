package com.leon.quiz.dto;

import java.util.List;

import lombok.Data;

@Data
public class QuizResultDTO {
    
    private List<Detail> result;
    private String userId;
    
    public static class Detail{
        private String questionId;
        private String answerIds;
    }
}
