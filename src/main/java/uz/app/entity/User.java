package uz.app.entity;

import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
@Builder
public class User {
    private String name;
    private  String email;
    private  String password;
    private Boolean isActive;
    private String smsCode;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}