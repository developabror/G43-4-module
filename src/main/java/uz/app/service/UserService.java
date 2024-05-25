package uz.app.service;

import uz.app.database.Db;
import uz.app.entity.User;
import uz.app.util.Context;

import static uz.app.util.Utils.scanner;
import static uz.app.util.Utils.strScanner;

public class UserService {
    private final Db db = Db.getInstance();

    public void service() {
        while (true) {
            System.out.println("""
                    0 exit
                    1 show info
                    2 edit info
                    """);
            switch (scanner.nextInt()) {
                case 0 -> {
                    System.out.printf("see you soon %s\n", Context.getCurrentUser().getName());
                    return;
                }
                case 1 -> {
                    User user = Context.getCurrentUser();
                    System.out.println("name = " + user.getName());
                    System.out.println("lastname = " + user.getLastname());
                    System.out.println("email = " + user.getEmail());
                    System.out.println("age = " + user.getAge());
                }
                case 2 -> {
                    User user = Context.getCurrentUser();
                    System.out.println("""
                            0 exit
                            1 name
                            2 lastname
                            3 email
                            4 password
                            5 age
                            """);

                    switch (scanner.nextInt()) {
                        case 0 -> {
                            break;
                        }
                        case 1 -> {
                            String s = strScanner.nextLine();
                            user.setName(s);
                        }
                        case 2 -> {
                            String s = strScanner.nextLine();
                            user.setLastname(s);
                        }
                        case 3 -> {
                            String s = strScanner.nextLine();
                            user.setEmail(s);
                        }
                        case 4 -> {
                            String s = strScanner.nextLine();
                            user.setPassword(s);

                        }
                        case 5 -> {
                            Integer s = scanner.nextInt();
                            user.setAge(s);
                        }
                    }
                    db.editUser(user);
                }
            }
        }
    }


    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }
}
