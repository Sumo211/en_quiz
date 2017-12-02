package com.leon.quiz.utils;

import com.leon.quiz.model.Level;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class QuestionLevelConverter implements AttributeConverter<Level, String> {

    @Override
    public String convertToDatabaseColumn(Level level) {
        return level.getShortName();
    }

    @Override
    public Level convertToEntityAttribute(String s) {
        return Level.fromShortName(s);
    }

}
