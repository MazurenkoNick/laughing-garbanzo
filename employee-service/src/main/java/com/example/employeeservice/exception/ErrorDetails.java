package com.example.employeeservice.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorDetails {

    private LocalDateTime dateTime;
    private String message;
    private String errorCode;
}
