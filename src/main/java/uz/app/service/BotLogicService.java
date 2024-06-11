package uz.app.service;

import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.app.payload.InlineString;
import uz.app.payload.UserState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static uz.app.util.Utils.*;

public class BotLogicService {
    private SendMessage sendMessage = new SendMessage();
    BotService botService = BotService.getInstance();
    private final ReplyMarkupService replyService = new ReplyMarkupService();
    private final InlineMarkupService inlineService = new InlineMarkupService();
    public static Map<Long, UserState> userState = new HashMap<>();
    private final Long adminId = 702192115l;
    public static Map<Long, String> adminResponse = new HashMap<>();


    public void messageHandler(Update update) {
        Long id = update.getMessage().getChat().getId();
        checkUserState(id);
        if (update.getMessage().hasPhoto()) {
            stateHandler(id, update);
            return;
        }
        sendMessage.setReplyMarkup(null);
        sendMessage.setChatId(id);

        String text = update.getMessage().getText();

        switch (text) {
            case "/start" -> {

                sendMessage.setText("welcome to bot");
                sendMessage.setReplyMarkup(replyService.keyboardMaker(mainMenu));
                botService.executeMessages(sendMessage);
            }
            case SEND_FILE -> {
                userState.put(id, UserState.SEND_FILE);
                sendMessage.setText("send your file to admin");
                botService.executeMessages(sendMessage);
            }
            case SEND_NUMBER -> {

            }
            case CONTACT_US -> {
                changeState(id, UserState.WRITE_TO_ADMIN);
                sendMessage.setText("please ask your question");
                botService.executeMessages(sendMessage);
            }
            default -> {
                stateHandler(id, update);
            }
        }

    }

    private void changeState(Long id, UserState state) {
        userState.put(id, state);
    }


    public void callbackHandler(Update update) {

        Long id = update.getCallbackQuery().getMessage().getChatId();
        checkUserState(id);
        sendMessage.setChatId(id);
        sendMessage.setReplyMarkup(null);
        adminResponse.put(id, update.getCallbackQuery().getData());
        sendMessage.setText("write your response!");
        userState.put(adminId, UserState.ADMIN_RESPONSE);
        botService.executeMessages(sendMessage);
    }


    private void checkUserState(Long id) {
        if (!userState.containsKey(id)) {
            userState.put(id, UserState.MAIN_MENU);
        }
    }


    public void stateHandler(Long id, Update update) {
        UserState state = userState.get(id);
        switch (state) {
            case ADMIN_RESPONSE -> {
                String userId = adminResponse.get(id);
                SendMessage response = new SendMessage();
                response.setChatId(userId);
                response.setText(update.getMessage().getText());


                sendMessage.setText("response send to user");
                botService.executeMessages(sendMessage, response);
                userState.put(id, UserState.MAIN_MENU);
            }
            case WRITE_TO_ADMIN -> {
                SendMessage sendMessageToAdmin = new SendMessage();
                sendMessageToAdmin.setChatId(adminId);
                sendMessageToAdmin.setText(update.getMessage().getText());
                sendMessageToAdmin.setReplyMarkup(inlineService.inlineMarkup(new InlineString[][]{{new InlineString("reply", String.valueOf(update.getMessage().getChatId()))}}));
                sendMessage.setText("send to admin");
                sendMessage.setReplyMarkup(replyService.keyboardMaker(mainMenu));
                botService.executeMessages(sendMessageToAdmin, sendMessage);
                userState.put(id, UserState.MAIN_MENU);
            }
            case SEND_FILE -> {
                if (!update.getMessage().hasPhoto())
                {
                    sendMessage.setText("please send photo!");
                    botService.executeMessages(sendMessage);
                    return;
                }
                List<PhotoSize> photos = update.getMessage().getPhoto();

                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setChatId(adminId);
                sendPhoto.setPhoto(new InputFile(photos.get(photos.size() - 1).getFileId()));
                botService.executeMessages(sendPhoto);
                userState.put(id, UserState.MAIN_MENU);
//                ForwardMessage forwardMessage = new ForwardMessage();
//                forwardMessage.setChatId(adminId);
//                forwardMessage.setFromChatId(id);  // From the same chat (forwarding within the same chat)
//                forwardMessage.setMessageId(update.getMessage().getMessageId());
//                botService.executeMessages(forwardMessage);
            }
        }
    }

    private static BotLogicService botLogicService;

    public static BotLogicService getInstance() {
        if (botLogicService == null) {
            botLogicService = new BotLogicService();
        }
        return botLogicService;
    }

}
