package com.example.ses_email_template;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final SESService service;

    @PostMapping("/mail")
    public void TestSES(@RequestBody EmailDto emailDto) {
        service.signupSendEmail(emailDto);
    }
}
