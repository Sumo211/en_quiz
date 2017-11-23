package com.leon.quiz.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {

    private String type;

    private String name;

    private String title;

    private List<AnswerDTO> choices;

    public QuestionDTO(String type, String name, String title) {
        this.type = type;
        this.name = name;
        this.title = title;
    }

    public QuestionDTO() {
    }

}
