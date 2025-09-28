package com.akash.QuizApplication.controller;


import com.akash.QuizApplication.model.Question;
import com.akash.QuizApplication.repository.QuestionRepository;
import com.akash.QuizApplication.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
    @Autowired
    QuestionRepository questionRepo;
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/quiz")
    public String quiz(Model model) {
        List<Question> questions = quizService.getAllQuestions();
        model.addAttribute("questions", questions);
        return "quiz";
    }

    @PostMapping("/submit")
    public String submitQuiz(@RequestParam Map<String, String> params, Model model) {
        int score = 0;

        List<Question> questions = questionRepo.findAll();
        List<String> correctAnswers = new ArrayList<>();
        List<String> wrongAnswers = new ArrayList<>();

        for (Question q : questions) {
            String userAnswer = params.get("answer_" + q.getId());
            if (userAnswer != null) {
                String correct = "";
                switch (q.getCorrectAnswer()) {
                    case "A": correct = q.getOptionA(); break;
                    case "B": correct = q.getOptionB(); break;
                    case "C": correct = q.getOptionC(); break;
                    case "D": correct = q.getOptionD(); break;
                }
                if (userAnswer.equals(q.getCorrectAnswer())) {
                    score++;
                    correctAnswers.add(correct);
                }
                else {
                    String selected = "";
                    switch (userAnswer) {
                        case "A": selected = q.getOptionA(); break;
                        case "B": selected = q.getOptionB(); break;
                        case "C": selected = q.getOptionC(); break;
                        case "D": selected = q.getOptionD(); break;
                    }
                    wrongAnswers.add(selected);
                }

            }
        }
        double percentage=((score *100.0)/questions.size());
        String percent=String.format("%.2f",percentage);

        model.addAttribute("score", score);
        model.addAttribute("total", questions.size());
        model.addAttribute("percentage", percent);
        model.addAttribute("correctAnswers", correctAnswers);
        model.addAttribute("wrongAnswers",wrongAnswers);
        return "result";
    }


}
