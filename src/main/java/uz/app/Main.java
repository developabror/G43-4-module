package uz.app;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import uz.app.entity.User;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner strScanner = new Scanner(System.in);

        Gson gson = new GsonBuilder().create();
        String url = "http://10.10.2.90:8080/api/user";
        HttpRequest.Builder httpRequest = HttpRequest.newBuilder(new URI(url));

        HttpClient client = HttpClient.newBuilder().build();


        while (true) {
            System.out.println("""
                    0 exit
                    1 create user
                    2 show users
                    3 edit user
                    4 delete user
                    """);
            switch (scanner.nextInt()) {
                case 0 -> {
                    System.out.println("see you soon!");
                    return;
                }
                case 1 -> {
                    User user = new User();
                    System.out.println("enter name");
                    user.setName(strScanner.nextLine());
                    System.out.println("enter username");
                    user.setUsername(strScanner.nextLine());
                    System.out.println("enter password");
                    user.setPassword(strScanner.nextLine());
                    System.out.println("enter age");
                    user.setAge(scanner.nextInt());
                    HttpRequest build = httpRequest
                            .header("Content-Type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                            .build();

                    client.send(build, HttpResponse.BodyHandlers.ofString());

                }
                case 2 -> {
                    HttpRequest build = httpRequest.GET().build();
                    HttpResponse<String> send = client.send(build, HttpResponse.BodyHandlers.ofString());
                    String body = send.body();
                    Type type = new TypeToken<ArrayList<User>>() {
                    }.getType();
                    ArrayList<User> users = gson.fromJson(body, type);
                    for (User user : users) {
                        System.out.println(user);
                    }
                }
                case 3 -> {
                    User user = new User();
                    System.out.println("enter id");
                    user.setId(strScanner.nextLine());
                    System.out.println("enter name");
                    user.setName(strScanner.nextLine());
                    System.out.println("enter username");
                    user.setUsername(strScanner.nextLine());
                    System.out.println("enter password");
                    user.setPassword(strScanner.nextLine());
                    System.out.println("enter age");
                    user.setAge(scanner.nextInt());
                    HttpRequest build = httpRequest
                            .uri(new URI(url+"/"+user.getId()))
                            .header("Content-Type", "application/json")
                            .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                            .build();

                    client.send(build, HttpResponse.BodyHandlers.ofString());
                }
                case 4 -> {
                    System.out.println("enter user id");
                    String id = strScanner.nextLine();
                    HttpRequest build = httpRequest
                            .uri(new URI(url+"/"+id))
                            .DELETE()
                            .build();

                    client.send(build, HttpResponse.BodyHandlers.ofString());
                }
            }
        }

//        URL url = new URL("https://jsonplaceholder.typicode.com/users");
//        URLConnection urlConnection = url.openConnection();
//        InputStream inputStream = urlConnection.getInputStream();
//        Type type = new TypeToken<ArrayList<User>>(){}.getType();
//        ArrayList<User> users= gson.fromJson(new InputStreamReader(urlConnection.getInputStream()), type);
//        for (User user : users) {
//            System.out.println(user);
//        }
    }
}
