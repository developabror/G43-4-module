package uz.app.entity;

import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "quruvchi", toBuilder = true)
public class Student extends Parent {
    private String studentId;
    private String univer;
    private String faculty;

}
