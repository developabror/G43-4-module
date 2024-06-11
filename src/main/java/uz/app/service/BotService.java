package uz.app.service;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotService extends TelegramLongPollingBot {
    private static final BotLogicService logicService = BotLogicService.getInstance();


    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasCallbackQuery()) {
            logicService.callbackHandler(update);
        } else if (update.hasMessage()) {
            logicService.messageHandler(update);
        }
    }


    @Override
    public String getBotUsername() {
        return "G43testBot";
    }

    @Override
    public String getBotToken() {

        return "7017603809:AAHXErC_SzoAntSatIu7JnoM6xCzI5w78Ng";
    }


    public void executeMessages(SendMessage... messages) {
        for (SendMessage message : messages) {
            try {
                execute(message);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static BotService botService;

    public static BotService getInstance() {
        if (botService == null) {
            botService = new BotService();
        }
        return botService;
    }

    @SneakyThrows
    public void executeMessages(ForwardMessage forwardMessage) {
        execute(forwardMessage);
    }

    @SneakyThrows
    public void executeMessages(SendPhoto sendPhoto) {
        execute(sendPhoto);
    }
}
