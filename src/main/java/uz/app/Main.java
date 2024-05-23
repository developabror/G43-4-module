package uz.app;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import lombok.val;
import uz.app.entity.Employee;
import uz.app.entity.Parent;
import uz.app.entity.Student;
import uz.app.entity.Worker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;

@Log
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Scanner strScanner = new Scanner(System.in);


    public static void main(String[] args)throws Exception {
        final var temp = "Hello";


//        Properties p = new Properties();
//
//        FileInputStream fileInputStream = new FileInputStream("D:\\PDP\\G43\\5-modul\\maven-app\\src\\main\\resources\\application.properties");
//        LogManager.getLogManager().readConfiguration(fileInputStream);
//        log.warning("warning");
//        log.log(Level.CONFIG,"message of config");
//        File file =new File("D:\\images.jpg");
//        Employee employee0 =new Employee("Ali","aliy@gmail.com",2_000_000.0);
//        Employee employee1 =new Employee("Ali","aliy@gmail.com",5_000_000.0);
//        System.out.println(employee0.equals(employee1));


//        Parent parent = new Parent();
//        System.out.println("hello");
//        System.out.println(parent);
//
////                .builder()
////                .name("Ali")
////                .surname("Aliyev")
////                .address("Navoiy")
////                .build();
//        Student student=
//                Student
//                        .quruvchi()
//
//                        .build();
//        Worker worker = new Worker();
//
//
//
//
//        student.setName("Jamshid");
//        student.setSurname("Sobirov");
//        student.setStudentId("1164653123");
//        student.setUniver("MIT");
//        student.setFaculty("Data science");
//
//        worker.setName("Jasur");
//        worker.setSurname("Komilov");
//        worker.setSalary(10_000_000.0);
//        worker.setEmployeeType("HYBRID");
//
//
//        System.out.println(parent);
//        System.out.println(student);
//        System.out.println(worker);

    }
}