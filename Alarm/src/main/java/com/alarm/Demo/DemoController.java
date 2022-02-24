package com.alarm.Demo;

import com.alarm.Dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final SimpMessagingTemplate messagingTemplate;
    private final DemoService demoService;

    @MessageMapping("/{classId}")
    public void message(@DestinationVariable("classId") Long classId) {
        messagingTemplate.convertAndSend("/sub/" + classId, "alarm socket connection completed.");
    }

    @PostMapping("/messaging")
    public void sendMessage(@RequestBody MessageDto messageDto) {
        demoService.createMessage(messageDto);
    }
}
