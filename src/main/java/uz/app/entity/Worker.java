package uz.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@ToString(callSuper = true)
@Setter
@Getter
public class Worker extends Parent{
    private String employeeType;
    private List<LocalDate> workingDays;
    private Double salary;

}
