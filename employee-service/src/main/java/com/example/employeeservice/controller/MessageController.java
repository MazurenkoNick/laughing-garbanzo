package com.example.employeeservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
@RefreshScope
@Log4j2
public class MessageController {

    @Value("${message.hidden}")
    private String hiddenMessage;

    @GetMapping("/hidden-message")
    public String getHiddenMessage() {
        return hiddenMessage;
    }
}
