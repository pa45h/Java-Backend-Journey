package org.pa45h.quizservice.controller;

import org.pa45h.quizservice.model.Answer;
import org.pa45h.quizservice.model.QuestionWrapper;
import org.pa45h.quizservice.model.Quiz;
import org.pa45h.quizservice.model.QuizDto;
import org.pa45h.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/generate")
    public ResponseEntity<Quiz> generateQuiz(@RequestBody QuizDto quizDto) {
        return quizService.generateQuiz(quizDto);
    }

    @GetMapping("get/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer quizId) {
        return quizService.getQuizQuestions(quizId);
    }

    @PostMapping("submit")
    public ResponseEntity<Integer> getResult(@RequestBody List<Answer> answers) {
        return quizService.getResult(answers);
    }

}
