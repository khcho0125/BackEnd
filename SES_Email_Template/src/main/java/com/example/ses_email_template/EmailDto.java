package com.example.ses_email_template;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailDto {

    private String email;
    private String username;
    private String link;
}
