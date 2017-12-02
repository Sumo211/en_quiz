package com.leon.quiz.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuizResultDTO {

    private String userId;

    private List<Detail> result;

    @Data
    public static class Detail {

        private String questionId;

        private String answerIds;

    }

}
