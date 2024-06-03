package com.robot.pilot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final String message;
    private final LocalDateTime timestamp;
    private final int status;
}
