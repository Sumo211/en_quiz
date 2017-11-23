package com.leon.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerDTO {

    private String value;

    private String text;

    public AnswerDTO() {
    }

}
