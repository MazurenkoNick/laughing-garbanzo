package com.example.departmentservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
@RefreshScope
public class MessageController {

    @Value("${message.hidden}")
    private String message;

    @GetMapping("/hidden-message")
    public String getMessage() {
        return message;
    }
}
