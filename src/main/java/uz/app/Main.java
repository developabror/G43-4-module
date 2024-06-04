package uz.app;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();



        URL url = new URL("https://www.cbu.uz/uz/arkhiv-kursov-valyut/json");

        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
//        uz.app.Currency[] currencies = gson.fromJson(new InputStreamReader(urlConnection.getInputStream()), uz.app.Currency[].class);
        Type type = new TypeToken<ArrayList<Currency>>(){}.getType();
        ArrayList<Currency> currencies= gson.fromJson(new InputStreamReader(urlConnection.getInputStream()), type);
        for (Currency currency : currencies) {
            System.out.println(currency);
        }


//        for (Post post : posts) {
//            System.out.println(post.getId());
//            System.out.println(post.getEmail());
//            System.out.println(post.getName());
//        }
    }
}
