package com.demo.Controller;

import com.demo.Dto.EmailDto;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@ResponseBody
@RestController
public class EmailController {

    private final JavaMailSender javaMailSender;

    public EmailController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @PostMapping("/CheckMail")
    @ResponseBody
    public String SendMail(@RequestBody EmailDto mail) {
        System.out.println(mail.getEmail());
        Random rnd = new Random();
        StringBuilder key = new StringBuilder();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getEmail());
        for(int i = 0; i < 3; i++) {
            int index = rnd.nextInt(25) + 65;
            key.append((char) index);
        }
        int numIndex = rnd.nextInt(9999) + 1000;
        key.append(numIndex);
        message.setSubject("인증번호 입력을 위한 메일 전송");
        message.setText("인증 번호 :"+key);
        javaMailSender.send(message);
        return key.toString();
    }
}
