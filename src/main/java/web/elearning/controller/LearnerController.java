package web.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.elearning.dto.ResponseData;
import web.elearning.dto.request.LearnerRequest;
import web.elearning.dto.response.LearnerResponse;
import web.elearning.service.LearnerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/learner")
public class LearnerController {
    private final LearnerService learnerService;

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody LearnerRequest request) {
        LearnerResponse response = learnerService.update(request, id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "edit success",
                response,
                null, null, null, null));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        LearnerResponse response = learnerService.getById(id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "find by id success",
                response,
                null, null, null, null));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(@RequestParam(value = "0", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        Page<LearnerResponse> response = learnerService.findAll(page, size);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "find all success",
                response.getContent(),
                response.getNumber(), response.getSize(), response.getTotalElements(), response.getTotalPages()));
    }
}
