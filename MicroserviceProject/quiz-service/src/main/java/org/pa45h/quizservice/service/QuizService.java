package org.pa45h.quizservice.service;

import org.pa45h.quizservice.feign.QuestionInterface;
import org.pa45h.quizservice.model.Answer;
import org.pa45h.quizservice.model.QuestionWrapper;
import org.pa45h.quizservice.model.Quiz;
import org.pa45h.quizservice.model.QuizDto;
import org.pa45h.quizservice.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionInterface questionInterface;

    public ResponseEntity<Quiz> generateQuiz(QuizDto quizDto) {
        try {
            List<Integer> questionIds = questionInterface.generateQuiz(quizDto.getCategory(), quizDto.getNumberOfQuestions()).getBody();
            Quiz quiz = new Quiz();
            quiz.setTitle(quizDto.getTitle());
            quiz.setQuestionIds(questionIds);
            quizRepository.save(quiz);

            return new ResponseEntity<>(quiz, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer quizId) {
        try {
            Quiz quiz = quizRepository.findById(quizId).get();
            List<Integer> questionIds = quiz.getQuestionIds();
            List<QuestionWrapper> questionWrappers = questionInterface.getQuestionsByIds(questionIds).getBody();

            return new ResponseEntity<>(questionWrappers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> getResult(List<Answer> answers) {
        try {
            return questionInterface.getResult(answers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
