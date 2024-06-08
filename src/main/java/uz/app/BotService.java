package uz.app;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class BotService extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
//        System.out.println(update.getMessage().getChat().getId());
//        System.out.println(update.getMessage().getChat().getFirstName());

//        System.out.println(Thread.currentThread().getName());
//        System.out.println(update.getMessage().getText());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());

        switch (update.getMessage().getText()) {
            case "/start" -> {
                sendMessage.setText("Assalamu alaykum. Botga xush kelibsiz");
                keyboardMaker(sendMessage,Utils.mainMenu);
            }
            case "info"->{
                sendMessage.setText("here is full info");
                keyboardMaker(sendMessage,Utils.info);
            }
            default -> sendMessage.setText(update.getMessage().getText());
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        System.out.println("success");
    }

    private static void keyboardMaker(SendMessage sendMessage, String[][] buttons) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        for (String[] button : buttons) {
            KeyboardRow row = new KeyboardRow();
            for (String s : button)
                row.add(s);
            keyboardRows.add(row);
        }
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

    @Override
    public String getBotUsername() {
        return "t.me/G43testBot";
    }

    @Override
    public String getBotToken() {
        return "7017603809:AAHXErC_SzoAntSatIu7JnoM6xCzI5w78Ng";
    }
}
