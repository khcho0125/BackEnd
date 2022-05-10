package com.firstsocketio.global.Error.Exception;

import com.firstsocketio.global.Error.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class RoomExistsException extends CustomException {

    public static final CustomException EXCEPTION = new RoomExistsException();

    private RoomExistsException() {
        super(ErrorCode.ROOM_EXISTS_ERROR);
    }
}
