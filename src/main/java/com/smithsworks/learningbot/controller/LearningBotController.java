package com.smithsworks.learningbot.controller;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.bot.LearningBot;
import com.smithsworks.learningbot.handler.Splitter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LearningBotController {

    private static final Logger log = LogManager.getLogger();

    @Qualifier("webhook")
    @Autowired
    private LearningBot learningWebHookBot;

    @Autowired
    private Splitter splitter;

    @Autowired
    public LearningBotController(@Qualifier("simple") LearningBot learningSimpleBot) {
        learningSimpleBot.getBot().setUpdatesListener(updates -> {
            updates.forEach(update -> this.splitter.split(update));
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    @GetMapping("/learningBot")
    public ResponseEntity<String> getHandle() {
        log.info("GET Method for learningBot");
        return new ResponseEntity<>("Hello Learning Bot!!!", HttpStatus.OK);
    }

    @PostMapping("/learningBot")
    public void postHandle(@RequestBody Update update) {
        log.info("POST Method for learningBot update: \"{}\" value", update.toString());
        learningWebHookBot.handle(update);
    }
}
