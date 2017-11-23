package com.leon.quiz.utils;

import com.leon.quiz.model.Type;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class QuestionTypeConverter implements AttributeConverter<Type, String> {

    @Override
    public String convertToDatabaseColumn(Type type) {
        return type.getShortName();
    }

    @Override
    public Type convertToEntityAttribute(String s) {
        return Type.fromShortName(s);
    }

}
