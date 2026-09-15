package org.pa45h.questionservice.controller;

import org.pa45h.questionservice.model.Answer;
import org.pa45h.questionservice.model.Question;
import org.pa45h.questionservice.model.QuestionWrapper;
import org.pa45h.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/add")
    public ResponseEntity<Boolean> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String category) {
        return questionService.getAllQuestionsByCategory(category);
    }

    @GetMapping("/generate/quiz/{category}/{noOfQues}")
    public ResponseEntity<List<Integer>> generateQuiz(@PathVariable String category, @PathVariable Integer noOfQues) {
        return questionService.generateQuiz(category, noOfQues);
    }

    @PostMapping("/get-questions-by-ids")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(@RequestBody List<Integer> ids) {
        return questionService.getQuestionsByIds(ids);
    }

    @PostMapping("/submit")
    public ResponseEntity<Integer> getResult(@RequestBody List<Answer> answer) {
        return questionService.getResult(answer);
    }
}
