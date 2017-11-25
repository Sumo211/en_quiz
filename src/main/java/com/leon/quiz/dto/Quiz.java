package com.leon.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Quiz {

    private String title;

    private double maxTimeToFinish;

    private List<Page> pages;

    public Quiz() {
    }

    @Data
    @AllArgsConstructor
    public static class Page {

        List<QuestionDTO> questions;

        public Page() {
        }

    }

}
