package com.smithsworks.learningbot.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

//@Component
public class Bot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            sendAnswer(message.getChatId(), message.getText());
        }
    }

    @Override
    public String getBotUsername() {
        return "DontLetMeForget";
    }

    @Override
    public String getBotToken() {
        return "798135138:AAFzgg4GwJQd-esTHi7ILQ76Y5LY4Rk6wsg";
    }

    private void sendAnswer(Long chatId, String replyText) {
        SendMessage sendMessage = new SendMessage()
                .setChatId(chatId)
                .setText(replyText);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

        }
    }
}
