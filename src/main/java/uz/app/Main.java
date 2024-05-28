package uz.app;


import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import uz.app.test.Run;
import uz.app.test.Test;
import uz.app.test.User;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
//    static List<String> names= null;
//    static {
//        Faker faker =new Faker();
//        Name name = faker.name();
//         names= Stream
//                .generate(() -> name.name())
//                .limit(200000).toList();
//    }

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(40);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 400; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                System.out.println("task completed!");
            });
        }
        System.out.println(System.currentTimeMillis() - start);
        executorService.shutdown();
        while (!executorService.awaitTermination(25,TimeUnit.SECONDS)){}
        long end = System.currentTimeMillis();
        System.out.println(end-start);
//        Random random =new Random();
//        List<Integer> list = Stream.generate(() -> random.nextInt(1, 10)).limit(100000000).toList();
//        long start = System.currentTimeMillis();
//        long count = list.stream().filter(integer -> integer > 5).count();
//        long end = System.currentTimeMillis();
//        System.out.println("time:: "+(end - start));
//        System.out.println(count);
//        System.out.println(new Date());
//        long count = names.stream().filter(s -> s.startsWith("A")).count();
//        System.out.println(new Date());
//        System.out.println(count);
//        ArrayList<User> users = new ArrayList<>(
//                Arrays.asList(
//                        new User("Ali", 23),
//                        new User("Vali", 23),
//                        new User("Asror", 41),
//                        new User("Axror", 63),
//                        new User("Sobir", 74),
//                        new User("Axmad", 14),
//                        new User("Jamshid", 46),
//                        new User("Sardor", 67),
//                        new User("Axmad", 24),
//                        new User("Asror", 52),
//                        new User("Rustam", 74)
//                )
//        );

//        User user = users.stream().filter(p -> p.getName().startsWith("S")).findAny().get();
//        System.out.println(user);
//        Function<User, String> function = (u) -> u.getName();
//        Predicate<User> predicate = (u) -> Character.toLowerCase(u.getName().charAt(0)) == 'S';

//        Optional<User> min = users.stream().min((o1, o2) -> o1.getAge() - o2.getAge());
//        Optional<User> max = users.stream().max((o1, o2) -> o1.getAge() - o2.getAge());
//        System.out.println(min.get());
//        System.out.println(max.get());
//        boolean b = users.stream().anyMatch((user) -> user.getName().contains("o"));
//        boolean b = users.stream().allMatch((user) -> user.getName().contains("o"));

//        Stream<String> info =
//                users
//                        .stream()
//                        .filter(predicate)
//                        .map(function)
//                        .peek(System.out::println);
//
//
////                        .distinct();
//        List<String> list = info.toList();
//        System.out.println(list);
//        System.out.println("process finished");


//        ArrayList<String> collectInfo = new ArrayList<>();
//        for (User user : users) {
//            if (predicate.test(user))
//                collectInfo.add(function.apply(user));
//        }
//        for (String temp: collectInfo){
//            System.out.println(temp);
//        }
    }
}
