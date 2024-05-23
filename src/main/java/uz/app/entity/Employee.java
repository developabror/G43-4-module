package uz.app.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@EqualsAndHashCode(of = {"name","email"})
public class Employee {
    private String name;
    private String email;
    private Double balance;
}
