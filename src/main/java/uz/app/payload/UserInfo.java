package uz.app.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfo {
    private String email;
    private String password;
}
