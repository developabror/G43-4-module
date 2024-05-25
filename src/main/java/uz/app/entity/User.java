package uz.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private final String id = UUID.randomUUID().toString();
    private String name;
    private String lastname;
    private String email;
    private String password;
    private Integer age;

}
/*
[
  {
  "name"       : "Ali",
  "lastname"   : "Aliyev",
  "email"      : "ali@gmail.com",
  "password"   : "root123",
  "age"        : 12
  },
  {
  "name"       : "Ali",
  "lastname"   : "Aliyev",
  "email"      : "ali@gmail.com",
  "password"   : "root123",
  "age"        : 12
  },
  {
  "name"       : "Ali",
  "lastname"   : "Aliyev",
  "email"      : "ali@gmail.com",
  "password"   : "root123",
  "age"        : 12
  }


]
 */
