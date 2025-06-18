package web.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.elearning.dto.ResponseData;
import web.elearning.dto.request.LearnerLevelRequest;
import web.elearning.dto.response.LearnerLevelResponse;
import web.elearning.dto.response.LevelResponse;
import web.elearning.dto.response.TopicResponse;
import web.elearning.service.LearnerLevelService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/learner-level")
public class LearnerLevelController {
    private final LearnerLevelService learnerLevelService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody LearnerLevelRequest request) {
        learnerLevelService.add(request);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "add success",
                "OK",
                null, null, null, null));
    }

    @GetMapping("/check")
    public ResponseEntity<?> check(@RequestParam(value = "learnerId") Long learnerId,
                                   @RequestParam(value = "levelId") Long levelId) {
        Boolean response = learnerLevelService.checkLearnerLevel(learnerId, levelId);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "find all success",
                response,
                null, null, null, null));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(@RequestParam(value = "page", required = false) Integer page,
                                     @RequestParam(value = "size", required = false) Integer size) {
        Page<LearnerLevelResponse> response = learnerLevelService.findAll(page, size);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "find all success",
                response.getContent(),
                response.getNumber(), response.getSize(), response.getTotalElements(), response.getTotalPages()));
    }

    @GetMapping("/findByLearner/{id}")
    public ResponseEntity<?> getTopicsByLearner(@PathVariable Long id) {
        List<LevelResponse> response = learnerLevelService.findAllByLearnerId(id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "find all success",
                response,
                null, null, null, null));
    }
}
