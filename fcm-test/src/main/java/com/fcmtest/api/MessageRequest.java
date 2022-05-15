package com.fcmtest.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MessageRequest {

    private String title;
    private String body;
    private String token;
}
