package com.smithsworks.learningbot.handler;

import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.builder.Builder;
import com.smithsworks.learningbot.builder.ReplyKeyboardBuilder;
import com.smithsworks.learningbot.data.HandlingPoint;
import com.smithsworks.learningbot.data.State;
import com.smithsworks.learningbot.data.UserState;
import com.smithsworks.learningbot.service.EngWordService;
import com.smithsworks.learningbot.service.I18nService;
import com.smithsworks.learningbot.service.SendService;
import com.smithsworks.learningbot.utils.HandlingPointUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * https://www.draw.io/#HRedLongCity%2Fds159%2Fmaster%2FNewWordScheme.xml
 */
@Service
@Qualifier("newWord")
public class NewWordUpdateHandler implements UpdateHandler {

    public static final String HANDLER_NAME = "newWord";
    private static final String ENTEREDWORD = "enteredWord";
    private static final String ENTEREDRUSSIANMEANING = "enteredRussianMeaning";
    private static final String ENTEREDEXTRARUSSIANMEANING = "enteredExtraRussianMeaning";
    public static final Pattern engPattern = Pattern.compile("[A-Za-z]*");
    public static final Pattern rusPattern = Pattern.compile("[А-Яа-я]*");


    @Autowired
    private I18nService i18nService;

    @Autowired
    private SendService sendService;

    @Autowired
    private ReplyKeyboardBuilder replyKeyboardBuilder;

    @Autowired
    private EngWordService engWordService;

    @Override
    public State handle(UserState userState, Update update) {
        HandlingPoint point = HandlingPointUtils.getSelectedHandlingPoint(userState, update);
        StringBuilder message = new StringBuilder();
        State newState = new State();
        String locale = i18nService.getLocale(userState);
        Builder builder = null;
        switch (point.getMethodName()) {
            case "enteredWord":
                builder = this.enteredWord(message, newState, locale);
                break;
        }
        boolean wasSent = sendService.sendMessage(update, builder, message.toString());
        return wasSent ? newState : null;
    }

    @Override
    public HandlingPoint getDefaultPoint() {
        return null;
    }

    @Override
    public Builder constructMainMenu(StringBuilder message, State newState, String locale) {
        message.append(i18nService.getI18nString("new.word.input.new.word", locale));
        newState
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, ENTEREDWORD, "default"));
        return getDefaultKeys(locale, newState);
    }

    private Builder enteredWord(StringBuilder message, State newState, String locale) {
        message.append(i18nService.getI18nString("new.word.input.rus.word.first.meaning", locale));
        newState.addHandlingPoint(new HandlingPoint(HANDLER_NAME, ENTEREDRUSSIANMEANING, "default"));
        return getDefaultKeys(locale, newState);
    }

    private Builder enteredRussianMeaning(StringBuilder message, State newState, String locale) {
        message.append(i18nService.getI18nString("new.word.input.rus.word.next.meaning", locale));
        newState.addHandlingPoint(new HandlingPoint(HANDLER_NAME, ENTEREDEXTRARUSSIANMEANING, "default"));
        return getDefaultKeys(locale, newState);
    }

    private Builder enteredExtraRussianMeaning(StringBuilder message, State newState, String locale) {
        message.append(i18nService.getI18nString("new.word.input.rus.word.next.meaning", locale));
        newState.addHandlingPoint(new HandlingPoint(HANDLER_NAME, ENTEREDEXTRARUSSIANMEANING, "default"));
        return getDefaultKeys(locale, newState);
    }

    private Builder enteredIncorrectEngWord(StringBuilder message, State newState, String locale) {
        message.append(i18nService.getI18nString("new.word.input.new.word.incorrect", locale));
        newState.addHandlingPoint(new HandlingPoint(HANDLER_NAME, ENTEREDRUSSIANMEANING, "default"));
        return getDefaultKeys(locale, newState);
    }

    private Builder enteredIncorrectRusWord(StringBuilder message, State newState, String locale) {
        message.append(i18nService.getI18nString("new.word.input.rus.word.meaning.incorrect", locale));
        newState.addHandlingPoint(new HandlingPoint(HANDLER_NAME, ENTEREDEXTRARUSSIANMEANING, "default"));
        return getDefaultKeys(locale, newState);
    }

    private Builder enteredIncorrectRusWordExtra(StringBuilder message, State newState, String locale) {
        message.append(i18nService.getI18nString("new.word.input.rus.word.meaning.incorrect", locale));
        newState.addHandlingPoint(new HandlingPoint(HANDLER_NAME, ENTEREDEXTRARUSSIANMEANING, "default"));
        return getDefaultKeys(locale, newState);
    }

    private boolean isCorrectEngWord(String word) {
        return !Objects.isNull(word) && engPattern.matcher(word).matches();
    }

    private boolean isCorrectRusWord(String word) {
        return !Objects.isNull(word) && rusPattern.matcher(word).matches();
    }

    private Builder addWordClass(UserState userState, StringBuilder message, State newState, String locale) {

    }

    private String handleAddWordClassMessage(UserState userState, State newState) {
        String locale = userState.getLocale();
        String result;
        switch (HandlingPointUtils.getDefaultHandlingPoint(userState).getMethodName()) {
            case ENTEREDRUSSIANMEANING:
                result = i18nService.getI18nString("new.word.input.rus.word.first.meaning", locale);
                break;
            case ENTEREDEXTRARUSSIANMEANING:
                result = i18nService.getI18nString("new.word.input.rus.word.next.meaning", locale);
                break;
            default:
                result = i18nService.getI18nString("new.word.input.new.word", locale);
        }
        return result;
    }

    private void handleEnteredWord()

    private Builder getDefaultKeys(String locale, State newState) {
        String edit = i18nService.getI18nString("new.word.edit", locale);
        String complete = i18nService.getI18nString("new.word.complete", locale);
        String adjective = i18nService.getI18nString("new.word.adjective", locale);
        String noun = i18nService.getI18nString("new.word.noun", locale);
        String numeral = i18nService.getI18nString("new.word.numeral", locale);
        String pronoun = i18nService.getI18nString("new.word.pronoun", locale);
        String adverb = i18nService.getI18nString("new.word.adverb", locale);
        String preposition = i18nService.getI18nString("new.word.preposition", locale);
        String newWordClass = "addWordClass";
        replyKeyboardBuilder
                .addRow()
                .addButton(edit)
                .addButton(complete)
                .addRow()
                .addButton(adjective)
                .addButton(noun)
                .addRow()
                .addButton(numeral)
                .addButton(pronoun)
                .addRow()
                .addButton(adverb)
                .addButton(preposition);
        newState
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, newWordClass, "new.word.adjective"))
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, newWordClass, "new.word.noun"))
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, newWordClass, "new.word.numeral"))
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, newWordClass, "new.word.pronoun"))
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, newWordClass, "new.word.adverb"))
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, newWordClass, "new.word.preposition"))
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, "complete", edit))
                .addHandlingPoint(new HandlingPoint(HANDLER_NAME, "edit", complete));
        return replyKeyboardBuilder;
    }

    private List<String> wrapClassesOfWord(List<String> classes) {
        List<String> result = null;
        if (!Objects.isNull(classes)) {
            String begin = "<i><b>";
            String end = "</b></i>\n";
            result = classes.stream()
                    .map(c -> c = begin.concat(c).concat(end))
                    .collect(Collectors.toList());
        }
        return result;
    }
}
