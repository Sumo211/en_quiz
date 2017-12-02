package com.leon.quiz;

import com.leon.quiz.service.ExcelConverterService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class EnglishQuizApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EnglishQuizApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EnglishQuizApplication.class);
    }

    @Bean
    ApplicationRunner applicationRunner(ExcelConverterService converterService) {
        return args -> converterService.importQuestions(new ClassPathResource("sample_data.xlsx").getFile());
    }

}
