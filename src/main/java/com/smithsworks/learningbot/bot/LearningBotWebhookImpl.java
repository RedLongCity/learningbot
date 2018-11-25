package com.smithsworks.learningbot.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.smithsworks.learningbot.utils.EnvironmentUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("webhook")
public class LearningBotWebhookImpl extends BotWebHookHandler implements LearningBot {

    private static final Logger log = LogManager.getLogger();

    private final String botToken;
    private final String botName;

    private final TelegramBot bot;

    public LearningBotWebhookImpl() {
        bot = new TelegramBot(this.getToken());
        String appSite = EnvironmentUtils.readEnvironment("OPENSHIFT_APP_DNS");
        log.info("Ip was init by: \"{}\" value", appSite);
        botToken = EnvironmentUtils.readEnvironment("BOT_TOKEN");
        log.info("BotToken was init by: \"{}\" value", botToken);
        botName = EnvironmentUtils.readEnvironment("BOT_NAME");
        log.info("BotName was init by: \"{}\" value", botName);
//        bot.execute(new SetWebhook().url(appSite + "/" + this.getToken()));
    }

    @Override
    public Object handle(Update update) {
        try {
            Message message = update.message();
            if (isStartMessage(message) && onStart(message)) {
                log.info("StartMessage Handled");
                return "ok";
            } else {
                log.info("WebHookUpdate handled for \"{}\" object", update.toString());
                onWebhookUpdate(update);
            }
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    public String getBotUsername() {
        return this.botName;
    }

    @Override
    void onWebhookUpdate(Update update) {
        Long chatId = update.message().chat().id();
        String text = update.message().text();
        log.info("ChatId: \"{}\" Text: \"{}\"", String.valueOf(chatId), text);
        if (text == null) text = "empty message?";
        String message = text + " _returned";
        log.info("Message: \"{}\"", message);
        bot.execute(new SendMessage(chatId, message));
    }

    @Override
    String getToken() {
        return this.botToken;
    }

    @Override
    public TelegramBot getBot() {
        return this.bot;
    }
}
