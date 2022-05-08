package com.firstsocketio.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final RoomService roomService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createRoom() {
        return roomService.create().toString();
    }
}
