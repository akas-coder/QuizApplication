package com.akash.QuizApplication.repository;

import com.akash.QuizApplication.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}

