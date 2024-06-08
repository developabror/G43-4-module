package uz.app;


import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Scanner strScanner = new Scanner(System.in);

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi api =new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new BotService());
    }
}
