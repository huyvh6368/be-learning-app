package web.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.elearning.dto.ResponseData;
import web.elearning.dto.request.ProcessRequest;
import web.elearning.dto.response.ProcessResponse;
import web.elearning.service.ProcessService;

@RestController
@RequestMapping("/api/process")
@RequiredArgsConstructor
public class ProcessController {
    private final ProcessService processService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ProcessRequest request) {
        processService.add(request);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "add success",
                "OK",
                null, null, null, null));
    }

    @PostMapping("/find/{id}")
    public ResponseEntity<?> add(@PathVariable Long id) {
        ProcessResponse response = processService.findById(id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "add success",
                response,
                null, null, null, null));
    }

    @GetMapping("/check")
    public ResponseEntity<?> check(@RequestParam(value = "learnerId") Long learnerId,
                                   @RequestParam(value = "questionId") Long questionId) {
        Boolean response = processService.check(learnerId, questionId);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "find all success",
                response,
                null, null, null, null));
    }

    @GetMapping("/findScore")
    public ResponseEntity<?> findScore(@RequestParam(value = "learnerId") Long learnerId,
                                       @RequestParam(value = "questionId") Long questionId) {
        ProcessResponse response = processService.findScore(learnerId, questionId);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "find all success",
                response,
                null, null, null, null));
    }
}
