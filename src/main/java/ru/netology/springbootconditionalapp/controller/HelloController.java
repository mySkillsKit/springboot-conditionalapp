package ru.netology.springbootconditionalapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${hello.from:undefined}")
    private String from;

    @GetMapping("/")
    private String hello() {
        return String.format("Hello from %s", from);
    }
}
