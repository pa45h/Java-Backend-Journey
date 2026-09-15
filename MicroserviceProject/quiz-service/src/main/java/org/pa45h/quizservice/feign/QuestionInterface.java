package org.pa45h.quizservice.feign;

import org.pa45h.quizservice.model.Answer;
import org.pa45h.quizservice.model.QuestionWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuestionInterface {
    @GetMapping("/question/generate/quiz/{category}/{noOfQues}")
    public ResponseEntity<List<Integer>> generateQuiz(@PathVariable String category, @PathVariable Integer noOfQues);

    @PostMapping("/question/get-questions-by-ids")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(@RequestBody List<Integer> ids);

    @PostMapping("/question/submit")
    public ResponseEntity<Integer> getResult(@RequestBody List<Answer> answer);
}
