package com.smithsworks.learningbot.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.smithsworks.learningbot.utils.EnvironmentUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("simple")
public class LearningBotImpl extends BotWebHookHandler implements LearningBot {

    private static final Logger log = LogManager.getLogger();

    private final String botToken;
    private final String botName;
    private final TelegramBot bot;

    public LearningBotImpl() {
        botToken = EnvironmentUtils.readEnvironment("BOT_TOKEN");
        log.info("BotToken was init by: \"{}\" value", botToken);
        botName = EnvironmentUtils.readEnvironment("BOT_NAME");
        log.info("BotName was init by: \"{}\" value", botName);
        bot = new TelegramBot(this.getToken());
    }

    @Override
    public Object handle(Update update) {//TODO clear after test
        try {
            Message message = update.message();
            if (isStartMessage(message) && onStart(message)) {
                log.info("StartMessage Handled");
                return "ok";
            } else {
                log.info("Simple Request handled for \"{}\" object", update.toString());
                onWebhookUpdate(update);
            }
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }//TODO clear after test

    @Override
    void onWebhookUpdate(Update update) {//TODO clear after test
        Long chatId = update.message().chat().id();
        String text = update.message().text();
        log.info("ChatId: \"{}\" Text: \"{}\"", String.valueOf(chatId), text);
        if (text == null) text = "empty message?";
        String message = text + "_returned";
        log.info("Message: \"{}\"", message);
        SendResponse response = bot.execute(new SendMessage(chatId, message).replyMarkup(
//                new ReplyKeyboardMarkup(
//                        new String[]{"first row button1", "first row button2"},
//                        new String[]{"second row button1", "second row button2"})
//                        .oneTimeKeyboard(true)   // optional
//                        .resizeKeyboard(true)    // optional
//                        .selective(true)        // optiona
                new InlineKeyboardMarkup(
                        new InlineKeyboardButton[]{
                                new InlineKeyboardButton("url").url("www.google.com"),
                                new InlineKeyboardButton("callback_data").callbackData("callback_data"),
                                new InlineKeyboardButton("switch_inline_query").switchInlineQuery("switch_inline_query"),
                                new InlineKeyboardButton("switch_inline_query").switchInlineQuery("switch_inline_query"),
                                new InlineKeyboardButton("switch_inline_query").switchInlineQuery("switch_inline_query"),
                                new InlineKeyboardButton("switch_inline_query").switchInlineQuery("switch_inline_query"),
                                new InlineKeyboardButton("switch_inline_query").switchInlineQuery("switch_inline_query")
                        }
                )
//                new ReplyKeyboardMarkup(
//                        new KeyboardButton[]{
//                                new KeyboardButton("text"),
//                                new KeyboardButton("contact").requestContact(true),
//                                new KeyboardButton("location").requestLocation(true)
//                        }
//                ).resizeKeyboard(true)

                )
//                        .replyMarkup(new ReplyKeyboardMarkup(new String[]{BUTTON_CB, BUTTON_EXCHANGE, BUTTON_OIL}))
        );
    }//TODO clear after test

    @Override
    String getToken() {
        return this.botToken;
    }

    @Override
    public TelegramBot getBot() {
        return this.bot;
    }
}
