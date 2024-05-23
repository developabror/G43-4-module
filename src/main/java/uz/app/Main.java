package uz.app;

import uz.app.entity.User;
import uz.app.payload.Confirmation;
import uz.app.payload.UserInfo;
import uz.app.service.UserService;

import java.util.Scanner;

public class Main {
    static Scanner scanner=new Scanner(System.in);
    static Scanner strScanner=new Scanner(System.in);

    public static void main(String[] args) {

        UserService userService=new UserService();


        while (true){
            System.out.println("""
                    0 exit
                    1 sign in
                    2 sign up 
                    3 confirm sms
                    """);
               switch (scanner.nextInt()){
                   case 0->{
                       return;
                   }
                   case 1->{
                       System.out.println("enter email");
                       String email = strScanner.nextLine();
                       System.out.println("enter password");
                       String password = strScanner.nextLine();
                       userService.signIn(new UserInfo(email,password));
                   }
                   case 2->{
                       System.out.println("enter email");
                       String email = strScanner.nextLine();
                       System.out.println("enter password");
                       String password = strScanner.nextLine();
                       userService.signUp(new UserInfo(email,password));
                   }
                   case 3->{
                       System.out.println("enter email");
                       String email = strScanner.nextLine();
                       System.out.println("enter code");
                       String code = strScanner.nextLine();
                       userService.checkSmsConfirmation(new Confirmation(email,code));
                   }
               }
        }

//        String test = "{\"name\":\"Ali\",\"password\":\"root123\",\"address\":\"uzb\",\"gender\":\"male\"}";
//        Gson gson =new GsonBuilder().create();
//        User user = gson.fromJson(test,User.class);
//        System.out.println(user);
    }
}