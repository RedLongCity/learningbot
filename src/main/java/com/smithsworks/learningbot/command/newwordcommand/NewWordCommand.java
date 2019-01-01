package com.smithsworks.learningbot.command.newwordcommand;

import com.pengrad.telegrambot.model.Update;
import com.smithsworks.learningbot.builder.ReplyKeyboardBuilder;
import com.smithsworks.learningbot.command.Command;
import com.smithsworks.learningbot.command.MainMenuCommand;
import com.smithsworks.learningbot.data.UserState;
import com.smithsworks.learningbot.service.I18nService;
import com.smithsworks.learningbot.service.SendService;
import com.smithsworks.learningbot.service.UserStateService;
import com.smithsworks.learningbot.utils.UpdateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
@Qualifier("newWordCommand")
public class NewWordCommand implements Command {

    public static final String COMMANDNAME = "newWordCommand";
    private static final Pattern engPattern = Pattern.compile("[A-Za-z]*");
    private final Map<String, List<String>> wordClasses;

    @Autowired
    private I18nService i18nService;

    @Autowired
    private ReplyKeyboardBuilder replyKeyboardBuilder;

    @Autowired
    private SendService sendService;

    @Autowired
    private UserStateService userStateService;

    @Autowired
    private Command addWordClassCommand;

    @Autowired
    private Command getEngMeaningCommand;

    public NewWordCommand() {
        this.wordClasses = new HashMap<>();
        List<String> engMeanings = new ArrayList<>();
        engMeanings.add(i18nService.getEngString("new.word.edit"));
        engMeanings.add(i18nService.getEngString("new.word.complete"));
        engMeanings.add(i18nService.getEngString("new.word.adjective"));
        engMeanings.add(i18nService.getEngString("new.word.noun"));
        engMeanings.add(i18nService.getEngString("new.word.numeral"));
        engMeanings.add(i18nService.getEngString("new.word.pronoun"));
        engMeanings.add(i18nService.getEngString("new.word.adverb"));
        engMeanings.add(i18nService.getEngString("new.word.preposition"));
        List<String> rusMeanings = new ArrayList<>();
        engMeanings.add(i18nService.getRusString("new.word.edit"));
        engMeanings.add(i18nService.getRusString("new.word.complete"));
        engMeanings.add(i18nService.getRusString("new.word.adjective"));
        engMeanings.add(i18nService.getRusString("new.word.noun"));
        engMeanings.add(i18nService.getRusString("new.word.numeral"));
        engMeanings.add(i18nService.getRusString("new.word.pronoun"));
        engMeanings.add(i18nService.getRusString("new.word.adverb"));
        engMeanings.add(i18nService.getRusString("new.word.preposition"));
        wordClasses.put(I18nService.ENGLOCALE, engMeanings);
        wordClasses.put(I18nService.RULOCALE, rusMeanings);
    }

    @Override
    public void handle(UserState userState, Update update) {
        if (MainMenuCommand.COMMANDNAME.equalsIgnoreCase(userState.getCurrentState().getCommandName())
                || (COMMANDNAME.equalsIgnoreCase(userState.getCurrentState().getCommandName())
                && !isCorrectEngMeaning(UpdateUtils.getMessageText(update)))) {
            userState.addStep(COMMANDNAME);
            userStateService.updateUserState(userState);
            this.execute(userState, update);
        } else {
            this.delegate(userState, update);
        }
    }

    @Override
    public void delegate(UserState userState, Update update) {
        String locale = userState.getLocale();
        if (wordClasses.get(locale).contains(UpdateUtils.getMessageText(update))) {
            addWordClassCommand.handle(userState, update);
        } else {
            getEngMeaningCommand.handle(userState, update);
        }
    }

    @Override
    public void execute(UserState userState, Update update) {//TODO вынести в Util
        String locale = userState.getLocale();
        String edit = i18nService.getI18nString("new.word.edit", locale);
        String complete = i18nService.getI18nString("new.word.complete", locale);
        String adjective = i18nService.getI18nString("new.word.adjective", locale);
        String noun = i18nService.getI18nString("new.word.noun", locale);
        String numeral = i18nService.getI18nString("new.word.numeral", locale);
        String pronoun = i18nService.getI18nString("new.word.pronoun", locale);
        String adverb = i18nService.getI18nString("new.word.adverb", locale);
        String preposition = i18nService.getI18nString("new.word.preposition", locale);
        String welcome = i18nService.getI18nString("new.word.input.new.word", locale);

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
        sendService.sendMessage(update, replyKeyboardBuilder, welcome);
    }

    private boolean isCorrectEngMeaning(String meaning) {
        return !Objects.isNull(meaning) && engPattern.matcher(meaning).matches();
    }
}
