package web.elearning.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.elearning.dto.ResponseData;
import web.elearning.dto.request.RankRequest;
import web.elearning.dto.response.RankResponse;
import web.elearning.service.RankService;

@RestController
@RequestMapping("/api/rank")
@RequiredArgsConstructor
public class RankController {
    private final RankService rankService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody RankRequest request) {
        RankResponse response = rankService.add(request);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "add success",
                response,
                null, null, null, null));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@RequestBody RankRequest request, @PathVariable Long id) {
        RankResponse response = rankService.update(request, id);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "update success",
                response,
                null, null, null, null));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                     @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<RankResponse> response = rankService.findAll(page, size);
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "find all success",
                response.getContent(),
                response.getNumber(), response.getSize(), response.getTotalElements(), response.getTotalPages()));
    }
}
