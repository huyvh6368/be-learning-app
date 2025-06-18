package web.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.elearning.dto.ResponseData;
import web.elearning.dto.request.LearnerTopicRequest;
import web.elearning.dto.response.LearnerTopicResponse;
import web.elearning.dto.response.LevelResponse;
import web.elearning.dto.response.TopicResponse;
import web.elearning.service.LearnerTopicService;

import java.util.List;

@RestController

@RequestMapping("/api/learner-topic")
@RequiredArgsConstructor
public class LearnerTopicController {
    private final LearnerTopicService learnerTopicService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody LearnerTopicRequest request) {
        System.out.println("learner id : " + request.getLearnerId() + "- ---- topic id : " + request.getTopicId());
        learnerTopicService.add(request);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "add success",
                "OK",
                null, null, null, null));
    }

    @GetMapping("/check")
    public ResponseEntity<?> check(@RequestParam(value = "learnerId") Long learnerId,
                                   @RequestParam(value = "topicId") Long topicId) {
        Boolean response = learnerTopicService.existsByLearnerIdAndTopicId(learnerId, topicId);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "find all success",
                response,
                null, null, null, null));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(@RequestParam(value = "page", required = false) Integer page,
                                     @RequestParam(value = "size", required = false) Integer size) {
        Page<LearnerTopicResponse> response = learnerTopicService.findAll(page, size);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "find all success",
                response.getContent(),
                response.getNumber(), response.getSize(), response.getTotalElements(), response.getTotalPages()));
    }

    @GetMapping("/findByLearner/{id}")
    public ResponseEntity<?> getTopicsByLearner(@PathVariable Long id) {
        List<TopicResponse> response = learnerTopicService.findAllTopicsByLearnerId(id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "find all success",
                response,
                null, null, null, null));
    }
}
