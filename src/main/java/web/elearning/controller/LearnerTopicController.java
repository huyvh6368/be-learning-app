package web.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.elearning.repository.AnswerRepository;
import web.elearning.repository.LearnerTopicRepository;
import web.elearning.repository.QuestionRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/correct-answer")
public class LearnerTopicController {
    private final LearnerTopicRepository learnerTopicRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

//    @PostMapping("/add")
//    public ResponseEntity<?> createProduct(@RequestBody CorrectAnswerRequest request) {
//        Question question = questionRepository.findById(Long.parseLong(request.getQuestionId())).orElseThrow(() -> new RuntimeException("Question not found"));
//        Answer answer = answerRepository.findById(Long.parseLong(request.getAnswerId())).orElseThrow(() -> new RuntimeException("answer not found"));
//        web.elearning.model.LearnerTopic learnerTopic = new web.elearning.model.LearnerTopic();
//        learnerTopic.setQuestion(question);
//        learnerTopic.setAnswer(answer);
//        this.learnerTopicRepository.save(learnerTopic);
//        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
//                "add success",
//                "oki",
//                null, null, null, null));
//    }
}
