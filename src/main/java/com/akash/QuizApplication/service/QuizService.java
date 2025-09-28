package com.akash.QuizApplication.service;

import com.akash.QuizApplication.model.Question;
import com.akash.QuizApplication.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private final QuestionRepository questionRepository;

    public QuizService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public int calculateScore(List<String> userAnswers) {
        List<Question> questions = getAllQuestions();
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (i < userAnswers.size() &&
                    questions.get(i).getCorrectAnswer().equals(userAnswers.get(i))) {
                score++;
            }
        }
        return score;
    }
}

