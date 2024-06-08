package uz.app;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static uz.app.Utils.*;

public class BotService extends TelegramLongPollingBot {


    static Map<Long, Integer> userState = new HashMap<>();

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        userState.putIfAbsent(chatId, 0);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);


        if (update.getMessage().hasContact()) {
            System.out.println(update.getMessage().getContact().getPhoneNumber());
            keyboardMaker(sendMessage, info);
            sendMessage.setText("here is info");
            userState.put(chatId,1);
            execute(sendMessage);
            return;
        }

        Integer state = userState.get(chatId);
        String text = update.getMessage().getText();

        switch (text) {
            case "/start" -> {
                state = 0;
                sendMessage.setText("Assalamu alaykum. Botga xush kelibsiz");
                keyboardMaker(sendMessage, Utils.mainMenu);
            }
            case Utils.INFO -> {
                state = 1;
                sendMessage.setText("here is full info");
                keyboardMaker(sendMessage, Utils.info);
            }
            case MAIN_ASKED -> {
                state = 2;
                keyboardMaker(sendMessage, new String[][]{{MAIN_ASKED}, {BACK}});
            }
            case FILIALS -> {
                state = 2;
                keyboardMaker(sendMessage, new String[][]{{FILIALS}, {BACK}});
            }
            case WORKING_DAYS -> {
                state = 2;
                keyboardMaker(sendMessage, new String[][]{{WORKING_DAYS}, {BACK}});
            }
            case PRODUCTS -> {
                state = 2;
                keyboardMaker(sendMessage, new String[][]{{PRODUCTS}, {BACK}});
            }
            case WORKING_HOURS -> {
                state = 2;
                keyboardMaker(sendMessage, new String[][]{{WORKING_HOURS}, {BACK}});
            }
            case BACK -> {
                if (state == 1) {
                    state = 0;
                    keyboardMaker(sendMessage, mainMenu);
                } else if (state == 2) {
                    state = 1;
                    keyboardMaker(sendMessage, info);
                }
            }
            default -> keyboardMaker(sendMessage, mainMenu);
        }
        userState.put(chatId, state);
        if (sendMessage.getText() == null) {
            sendMessage.setText(text);
        }
        execute(sendMessage);
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
            for (String s : button) {
                if (s.equals(SEND_NUMBER)) {
                    KeyboardButton keyboardButton = new KeyboardButton(s);
                    keyboardButton.setRequestContact(true);
                    row.add(keyboardButton);
                } else {
                    row.add(s);
                }
            }
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
