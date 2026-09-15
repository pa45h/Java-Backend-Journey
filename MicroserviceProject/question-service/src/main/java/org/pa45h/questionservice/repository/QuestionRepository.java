package org.pa45h.questionservice.repository;

import org.pa45h.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    public List<Question> findByCategory(String category);

    @Query(value = "SELECT id FROM question WHERE category=:category ORDER BY RAND() LIMIT :numberOfQuestions", nativeQuery = true)
    public List<Integer> getQuiz(String category, Integer numberOfQuestions);
}
