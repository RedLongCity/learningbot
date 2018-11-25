package com.smithsworks.learningbot.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.smithsworks.learningbot.service.I18nService;
import com.smithsworks.learningbot.utils.EnvironmentUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("simple")
public class LearningBotImpl extends BotWebHookHandler implements LearningBot {

    @Autowired
    private I18nService i18nService;

    private static final Logger log = LogManager.getLogger();

    private final String botToken;
    private final String botName;
    public final String BUTTON_CB = "ЦБ";
    public final String BUTTON_EXCHANGE = "Биржа";
    public final String BUTTON_OIL = "Нефть";

    private final TelegramBot bot;

    public LearningBotImpl() {
        botToken = EnvironmentUtils.readEnvironment("BOT_TOKEN");
        log.info("BotToken was init by: \"{}\" value", botToken);
        botName = EnvironmentUtils.readEnvironment("BOT_NAME");
        log.info("BotName was init by: \"{}\" value", botName);
        bot = new TelegramBot(this.getToken());
        bot.setUpdatesListener(updates -> {
            updates.forEach(update -> this.handle(update));
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    @Override
    public Object handle(Update update) {
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
    }

    @Override
    void onWebhookUpdate(Update update) {
        Long chatId = update.message().chat().id();
        String text = update.message().text();
        log.info("ChatId: \"{}\" Text: \"{}\"", String.valueOf(chatId), text);
        if (text == null) text = "empty message?";
        String message;
        switch (text) {
            case BUTTON_CB:
                message = BUTTON_CB;
                break;
            case BUTTON_EXCHANGE:
                message = BUTTON_CB;
                break;
            case BUTTON_OIL:
                message = BUTTON_OIL;
                break;
            default:
                message = text + "_returned";
        }
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
                        },
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
