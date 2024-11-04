package com.felipeantunes.Zimbaue.model.dto;

import com.felipeantunes.Zimbaue.model.entity.User;
import com.felipeantunes.Zimbaue.model.enums.Role;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer id;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private Role role;

    public static User toUserEntity(UserDTO userDTO){
        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .build();
    }


}
