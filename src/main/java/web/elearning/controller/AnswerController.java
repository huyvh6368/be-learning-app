package web.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.elearning.dto.ResponseData;
import web.elearning.dto.request.AnswerRequest;
import web.elearning.dto.response.AnswerResponse;
import web.elearning.service.AnswerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/answer")
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/add")
    public ResponseEntity<?> createProduct(@RequestBody AnswerRequest request) {
        AnswerResponse response = answerService.add(request);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "add success",
                response,
                null, null, null, null));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody AnswerRequest request) {
        AnswerResponse response = answerService.update(request, id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "edit success",
                response,
                null, null, null, null));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(@RequestParam(value = "questionId") Long questionId) {
        List<AnswerResponse> response = answerService.findAllByQuestionId(questionId);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "find all success",
                response,
                null, null, null, null));
    }
}
