package web.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import web.elearning.dto.ResponseData;
import web.elearning.dto.request.AnswerRequest;
import web.elearning.dto.request.CorrectAnswerRequest;
import web.elearning.dto.response.AnswerResponse;
import web.elearning.model.Answer;
import web.elearning.model.CorrectAnswer;
import web.elearning.model.Question;
import web.elearning.repository.AnswerRepository;
import web.elearning.repository.CorrectAnswerRepository;
import web.elearning.repository.QuestionRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/correct-answer")
public class CorrectAnswerController {
    private final CorrectAnswerRepository correctAnswerRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @PostMapping("/add")
    public ResponseEntity<?> createProduct(@RequestBody CorrectAnswerRequest request) {
        Question question = questionRepository.findById(Long.parseLong(request.getQuestionId())).orElseThrow(() -> new RuntimeException("Question not found"));
        Answer answer = answerRepository.findById(Long.parseLong(request.getAnswerId())).orElseThrow(() -> new RuntimeException("answer not found"));
        CorrectAnswer correctAnswer = new CorrectAnswer();
        correctAnswer.setQuestion(question);
        correctAnswer.setAnswer(answer);
        correctAnswerRepository.save(correctAnswer);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "add success",
                "oki",
                null, null, null, null));
    }
}
