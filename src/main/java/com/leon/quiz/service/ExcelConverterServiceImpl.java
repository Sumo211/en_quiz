package com.leon.quiz.service;

import com.leon.quiz.model.Answer;
import com.leon.quiz.model.Level;
import com.leon.quiz.model.Question;
import com.leon.quiz.model.Type;
import com.leon.quiz.repository.AnswerRepository;
import com.leon.quiz.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ExcelConverterServiceImpl implements ExcelConverterService {

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    public ExcelConverterServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public void importQuestions(File source) {
        try {
            FileInputStream inputStream = new FileInputStream(source);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row currentRow : sheet) {
                Question question = new Question();
                List<String> answers = new ArrayList<>();
                Iterator<Cell> cells = currentRow.cellIterator();
                while (cells.hasNext()) {
                    Cell currentCell = cells.next();
                    int index = currentCell.getColumnIndex();

                    switch (index) {
                        case 0:
                            question.setContent(getCellValue(currentCell));
                            break;
                        case 1:
                            question.setType(Type.fromShortName(getCellValue(currentCell)));
                            break;
                        case 2:
                            question.setLevel(Level.fromShortName(getCellValue(currentCell)));
                            break;
                        default:
                            answers.add(getCellValue(currentCell));
                            break;
                    }
                }

                answerRepository.save(convertToAnswers(answers, questionRepository.save(question)));
            }
        } catch (IOException ex) {
            log.error("Error: {}", ex);
        }
    }

    private String getCellValue(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int)cell.getNumericCellValue());
            default:
                return "";
        }
    }

    private List<Answer> convertToAnswers(List<String> input, Question question) {
        List<Answer> answers = new ArrayList<>();

        Answer answer;
        for (int i = 0; i < input.size(); i = i + 2) {
            answer = new Answer();
            answer.setQuestion(question);
            answer.setContent(input.get(i));
            answer.setCorrect("1".equalsIgnoreCase(input.get(i + 1)));
            answers.add(answer);
        }

        return answers;
    }

}
