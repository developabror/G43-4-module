package uz.app.util;

import uz.app.entity.User;

public class Context {
    private static User currentUser;
    public static void setUser(User user){
        currentUser = user;
    }

    public static User getCurrentUser(){
        return currentUser;
    }

}
