package uz.app.service;

import uz.app.entity.User;
import uz.app.payload.Confirmation;
import uz.app.payload.UserInfo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;

public class UserService {
    HashSet<User> users = new HashSet<>();
    SimpleMailSender simpleMailSender=new SimpleMailSender();
    public void signUp(UserInfo userInfo) {
        User user = User.builder()
                .email(userInfo.getEmail())
                .password(userInfo.getPassword())
                .isActive(false)
                .build();

        if (users.add(user)) {
            user.setSmsCode(generateCode());
            simpleMailSender.sendSmsToUser(user.getEmail(),user.getSmsCode());
            System.out.println("success, please confirm sms!");
        } else {
            System.out.println("this email has already registered!");
        }
    }

    public void checkSmsConfirmation(Confirmation confirmation) {
        Optional<User> optionalUser = findUser(confirmation.email());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (user.getSmsCode().equals(confirmation.code())){
                user.setIsActive(true);
                return;
            }
        }
        System.out.println("wrong email");
    }

    public void signIn(UserInfo userInfo) {
        Optional<User> optionalUser = findUser(userInfo.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(userInfo.getPassword())) {
                if (user.getIsActive()) {
                    System.out.println("welcome");
                } else {
                    System.out.println("please confirm sms sent to your email::" + user.getEmail());
                }
                return;
            }
        }
        System.out.println("email or password wrong!");

    }

    private Optional<User> findUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    private String generateCode() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(0, 10));
        }
        return sb.toString();
    }

}
