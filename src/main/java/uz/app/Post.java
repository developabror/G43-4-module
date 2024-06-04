package uz.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Integer postId;
    private Integer id;
    private String name;
    private String email;
    private String body;
}
