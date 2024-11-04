package com.felipeantunes.Zimbaue.model.dto;

import com.felipeantunes.Zimbaue.model.entity.Post;
import com.felipeantunes.Zimbaue.model.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Integer id;

    private String title;

    private String description;

    private String link;

    private LocalDateTime createdAt;

    @NonNull
    private UserDTO userDTO;

    public Post toPostEntity(){
        User user2 = UserDTO.toUserEntity(this.getUserDTO());
        return Post.builder()
                .title(this.title)
                .description(this.description)
                .link(this.link)
                .user(user2)
                .build();
    }

}
