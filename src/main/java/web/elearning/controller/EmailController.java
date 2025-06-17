package web.elearning.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.elearning.dto.EmailRequest;
import web.elearning.dto.ResponseData;
import web.elearning.service.MailService;


@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {
    private final MailService mailService;

    @PostMapping("/forgot")
    public ResponseEntity<?> forgot(@RequestBody EmailRequest emailRequest) throws MessagingException {
        mailService.sendNewPasswordEmail(emailRequest.getEmail());
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK.value(),
                "send mail successfully",
                "Please check your email address.",
                null, null, null, null));
    }
}