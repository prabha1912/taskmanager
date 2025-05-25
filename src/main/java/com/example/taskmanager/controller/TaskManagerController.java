package com.example.taskmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskManagerController {
    @GetMapping("/hello")
    public String greeting()
    {
        return "Hello Welcome!";
    }
}
