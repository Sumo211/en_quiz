package com.leon.quiz.dto;

import lombok.Data;

@Data
public class FinalResultDTO {

    private String userId;

    private int numOfRightAnswers;

    private int totalQuestions;

    public FinalResultDTO() {
    }

}
