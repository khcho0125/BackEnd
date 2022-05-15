package com.fcmtest.api;

import com.fcmtest.FCMService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FCMController {

    private final FCMService fcmService;

    @PostMapping("/push")
    public void sendMessage(@RequestBody MessageRequest request) {
        fcmService.sendMessage(request.getTitle(), request.getBody(), request.getToken());
    }

    @GetMapping("/token")
    public TokenDto token() {
        return new TokenDto(fcmService.getAccessToken());
    }
}
