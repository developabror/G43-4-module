package uz.app;

import com.github.javafaker.*;
import lombok.extern.java.Log;
import uz.app.entity.User;
import uz.app.service.AuthService;

import java.util.*;

@Log
public class Main {
    public static void main(String[] args) throws Exception {
        new AuthService().auth();
    }
}
