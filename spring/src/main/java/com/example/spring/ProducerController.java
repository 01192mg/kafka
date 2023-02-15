package com.example.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProducerController {
    private final ProducerService producerService;

    @RequestMapping("/publish")
    public String publish(String message) {
        producerService.send(message);
        return "Published a message: " + message;
    }

    @RequestMapping("/publish2")
    public String publishWithCallback(String message) {
        producerService.sendWithCallback(message);
        return "Published a message with callback: " + message;
    }

    @RequestMapping("/publish3")
    public String publishJson(MyMessage message) {
        producerService.sendJson(message);
        return "Published a message with callback: " + message.name() + ", " + message.message();
    }
}
