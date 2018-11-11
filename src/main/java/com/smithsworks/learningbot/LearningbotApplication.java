package com.smithsworks.learningbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@EnableMongoRepositories
public class LearningbotApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LearningbotApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LearningbotApplication.class);
    }
}
