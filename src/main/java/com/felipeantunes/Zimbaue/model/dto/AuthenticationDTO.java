package com.felipeantunes.Zimbaue.model.dto;

import com.felipeantunes.Zimbaue.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationDTO {
    private String email;
    private String password;
    private Role role;
    private String name;
}
