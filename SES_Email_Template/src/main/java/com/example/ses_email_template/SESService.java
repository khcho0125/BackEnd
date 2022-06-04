package com.example.ses_email_template;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.CreateTemplateRequest;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import com.amazonaws.services.simpleemail.model.Template;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SESService {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    public void signupSendEmail(EmailDto emailDto) {
        Destination des = new Destination();
        des.setToAddresses(List.of(emailDto.getEmail()));

        Map<String, String> data = new HashMap<>();
        data.put("user", emailDto.getUsername());
        data.put("link", emailDto.getLink());
        String templateData = new Gson().toJson(data);

        SendTemplatedEmailRequest emailRequest = new SendTemplatedEmailRequest();
        emailRequest.setTemplate("Test");
        emailRequest.setDestination(des);
        emailRequest.setSource("khcho0125@dsm.hs.kr");
        emailRequest.setTemplateData(templateData);

        amazonSimpleEmailService.sendTemplatedEmail(emailRequest);
    }

    public void Template(String HtmlTemplate) { // 프로젝트에서 Email Template 넣어주기
        Template template = new Template();
        template.setTemplateName("Test");
        template.setSubjectPart("AWS SES에 오신 것을 환영합니다.");
        template.setTextPart(null);
        template.setHtmlPart(HtmlTemplate);

        CreateTemplateRequest request = new CreateTemplateRequest().withTemplate(template);
        amazonSimpleEmailService.createTemplate(request);
    }
}
