package com.smithsworks.learningbot.service;

import com.smithsworks.learningbot.data.UserState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;

@Service
public class I18nService {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    public String getI18nString(String code, String local) {
        String result = "Пустая строка (";
        try {
            result = messageSourceAccessor.getMessage(code, new Locale(local));
        } catch (NoSuchMessageException e) {
            log.info("MessageSourceAccessor key didn't find: \"{}\" Locale: \"{}\"", code, local);
        }
        return result;
    }

    public String getLocale(UserState userState) {
        return Objects.isNull(userState) ? "ru" : userState.getLocale();
    }

}
