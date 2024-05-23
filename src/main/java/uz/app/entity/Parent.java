package uz.app.entity;

import lombok.*;


@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Parent {
    private String name;
    private String surname;
    private String address;
}
