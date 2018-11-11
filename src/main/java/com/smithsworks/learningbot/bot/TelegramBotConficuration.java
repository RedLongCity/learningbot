package com.smithsworks.learningbot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

//@Configuration
//@ConditionalOnClass(TelegramBotsApi.class)
public class TelegramBotConficuration {

//    @Autowired
    private Bot bot;
//    private List<TelegramLongPollingBot> pollingBots = new ArrayList<>();

//    @PostConstruct
    public void start() {
        TelegramBotsApi api = getApi();
        try {
            api.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
//        pollingBots.forEach(bot -> {
//            try {
//                api.registerBot(bot);
//            } catch (TelegramApiRequestException e) {
//                e.printStackTrace();
//            }
//        });
    }

//    @Bean
//    @ConditionalOnMissingBean
    public TelegramBotsApi getApi() {
        return new TelegramBotsApi();
    }

}
