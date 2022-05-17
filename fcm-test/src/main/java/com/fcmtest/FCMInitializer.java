package com.fcmtest;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@Service
@Slf4j
public class FCMInitializer {

    public static final String JSON = "static/fcm-test-2b404-firebase-adminsdk-pfb9o-cbed4064a9.json";

    @PostConstruct
    public void initialize() {
        ClassPathResource resource = new ClassPathResource(JSON);

        try (InputStream is = resource.getInputStream()) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(is)).build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("FirebaseApp initialization complete");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
