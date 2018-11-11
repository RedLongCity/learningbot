package com.smithsworks.learningbot.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api")
public class MainController {

    private static final Logger log = LogManager.getLogger();

    @GetMapping("/hello")
    public ResponseEntity<String> getAll() {
        log.info("Default hello method was run");
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
}
