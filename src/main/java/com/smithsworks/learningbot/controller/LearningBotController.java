package com.smithsworks.learningbot.controller;

import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.bot.LearningBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LearningBotController {

    @Autowired
    private LearningBot learningBot;

    @GetMapping("/learningBot")
    public ResponseEntity<String> getHandle() {
        return new ResponseEntity<>("Hello Learning Bot!!!", HttpStatus.OK);
    }

    @PostMapping("/learningBot")
    public void postHandle(@RequestBody Update update) {
        learningBot.handle(update);
    }
}
