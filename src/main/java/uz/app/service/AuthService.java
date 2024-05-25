package uz.app.service;

import uz.app.database.Db;
import uz.app.entity.User;
import uz.app.util.Context;

import java.util.Optional;

import static uz.app.util.Utils.*;

public class AuthService {
    private final Db db =Db.getInstance();
    private final UserService userService=UserService.getInstance();

    public void auth(){
        while (true){
            System.out.println("""
                    0 exit
                    1 sign in
                    2 sign up
                    3 show users
                    """);
            switch (scanner.nextInt()){
                case 0->{
                    System.out.println("see you!");
                    return;
                }
                case 1->{
                    System.out.println("enter email");
                    String email = strScanner.nextLine();
                    System.out.println("enter password");
                    String password = strScanner.nextLine();
                    Optional<User> optionalUser = db.getUserByEmailAndPassword(email, password);
                    if (optionalUser.isPresent()){
                        Context.setUser(optionalUser.get());
                        userService.service();
                    }else {
                        System.out.println("user not found!");
                    }
                }
                case 2->{
                    System.out.println("enter email");
                    String email = strScanner.nextLine();
                    System.out.println("enter password");
                    String password = strScanner.nextLine();
                    System.out.println("enter name");
                    String name = strScanner.nextLine();
                    System.out.println("enter lastname");
                    String lastname = strScanner.nextLine();
                    System.out.println("enter age");
                    Integer age = scanner.nextInt();

                    User user=new User(name,lastname,email,password,age);
                    db.saveUser(user);

                }
                case 3->{
                    for (User user : db.userList()) {
                        System.out.println(user);
                    }
                }
            }
        }
    }


    private static AuthService authService;
    public static AuthService getInstance(){
        if (authService==null){
            authService=new AuthService();
        }
        return authService;
    }
}
