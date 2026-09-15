package org.pa45h.quizservice.model;

import lombok.Data;

@Data
public class QuizDto {
    private String title;
    private String category;
    private Integer numberOfQuestions;
}
