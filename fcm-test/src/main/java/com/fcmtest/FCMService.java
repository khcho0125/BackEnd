package com.fcmtest;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.fcmtest.FCMInitializer.JSON;

@Service
@Slf4j
public class FCMService {

    public void sendMessage(String title, String body, String token) {
        try {
            Message message = Message.builder()
                    .setNotification(Notification.builder()
                            .setTitle(title)
                            .setBody(body).build())
                    .setToken(token)
                    .build();

            String response = FirebaseMessaging.getInstance().sendAsync(message).get();
            log.info("Success Send Message : {}", response);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public String getAccessToken() {
        try {
            GoogleCredentials googleCredentials = GoogleCredentials
                    .fromStream(new ClassPathResource(JSON).getInputStream())
                    .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

            googleCredentials.refreshIfExpired();
            return googleCredentials.getAccessToken().getTokenValue();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
