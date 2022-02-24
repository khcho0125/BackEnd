package com.alarm.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {

    private Long classId;
    private String message;
    private String senderId;
}
