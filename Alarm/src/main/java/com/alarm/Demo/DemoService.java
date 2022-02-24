package com.alarm.Demo;

import com.alarm.Dto.MessageDto;
import com.alarm.WebSocket.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DemoService {

    private final AlarmService alarmService;

    public void createMessage(MessageDto messageDto) {
        alarmService.alarmByMessage(messageDto);
    }
}
