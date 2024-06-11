package uz.app.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.app.Db;
import uz.app.entity.Test;
import uz.app.entity.User;
import uz.app.payload.InlineString;

import java.lang.annotation.Target;
import java.util.*;

import static uz.app.util.Utils.*;

public class BotLogicService {
    private SendMessage sendMessage = new SendMessage();
    Db db=new Db();
    BotService botService = BotService.getInstance();
    private final ReplyMarkupService replyService = new ReplyMarkupService();
    private final InlineMarkupService inlineService = new InlineMarkupService();

    private Set<User> users = new HashSet<>();

//    private final Long adminId = 702192115l;


    public void messageHandler(Update update) {
        Long id = update.getMessage().getChat().getId();
        users.add(new User(id.toString(), "main", null));
        User currentUser = getUserById(id);
        sendMessage.setReplyMarkup(null);
        sendMessage.setChatId(id);

        String text = update.getMessage().getText();

        switch (text) {
            case "/start" -> {

                sendMessage.setText("welcome to bot");
                sendMessage.setReplyMarkup(replyService.keyboardMaker(mainMenu));
                botService.executeMessages(sendMessage);
            }
            case INFO -> {
//                userState.put(id, UserState.SEND_FILE);
                sendMessage.setText("this is online test to check your knowled about java");
                botService.executeMessages(sendMessage);
            }
            case START_TEST -> {
                sendMessage.setText("Are you ready");
                sendMessage.setReplyMarkup(replyService.keyboardMaker(new String[][]{{"ready", "back"}}));
                botService.executeMessages(sendMessage);
            }
            case "ready" -> {
//                ArrayList<Test> tests = new ArrayList<>();
//                Set<Integer> taskcount = new HashSet<>();
//                Random random=new Random();
//                while (taskcount.size()<10){
//                    taskcount.add(random.nextInt(db.tests.size()));
//                }
//                for (Integer i : taskcount) {
//                    tests.add(db.tests.get(i));
//                }
//                currentUser.setCurrentTest(tests);


                sendMessage.setText("test started, question....");
                sendMessage.setReplyMarkup(inlineService.inlineMarkup(new InlineString[][]{
                        {new InlineString("true","false"),new InlineString("false","true")},
                        {new InlineString("null","false"),new InlineString("undefined","true")},

                }));
//                sendMessage.setReplyMarkup();
                botService.executeMessages(sendMessage);
            }

            case CONTACT_US -> {
                sendMessage.setText("please ask your question");
                botService.executeMessages(sendMessage);
            }
            default -> {

            }
        }

    }

    private User getUserById(Long id) {

        for (User user : users) {
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }


    public void callbackHandler(Update update) {

        Long id = update.getCallbackQuery().getMessage().getChatId();

        sendMessage.setChatId(id);
        sendMessage.setReplyMarkup(null);
        sendMessage.setText("write your response!");
        botService.executeMessages(sendMessage);
    }


    private static BotLogicService botLogicService;

    public static BotLogicService getInstance() {
        if (botLogicService == null) {
            botLogicService = new BotLogicService();
        }
        return botLogicService;
    }

}
