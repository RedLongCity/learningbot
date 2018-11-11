package com.smithsworks.learningbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories
public class LearningbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningbotApplication.class, args);
	}
}
