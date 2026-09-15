package org.pa45h.questionservice.service;

import org.pa45h.questionservice.model.Answer;
import org.pa45h.questionservice.model.QuestionWrapper;
import org.pa45h.questionservice.model.Question;
import org.pa45h.questionservice.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<Boolean> addQuestion(Question question) {
        try {
            questionRepository.save(question);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getAllQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> generateQuiz(String category, Integer numberOfQuestions) {
        try {
            return new ResponseEntity<>(questionRepository.getQuiz(category, numberOfQuestions), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(List<Integer> ids) {
        try {
            List<QuestionWrapper> wrapper = new ArrayList<>();
            List<Question> questions = new ArrayList<>();

            for (Integer id : ids) {
                questions.add(questionRepository.findById(id).get());
            }

            for (Question question : questions) {
                QuestionWrapper questionWrapper = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4(), question.getCategory());

                wrapper.add(questionWrapper);
            }

            return new ResponseEntity<>(wrapper, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> getResult(List<Answer> answer) {
        try {
            int result = 0;

            for (Answer a : answer) {
                if (a.getAnswer().equals(questionRepository.findById(a.getId()).get().getRightAnswer())) {
                    result++;
                }
            }

            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
