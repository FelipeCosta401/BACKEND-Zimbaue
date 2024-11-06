package com.felipeantunes.Zimbaue.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private UserDTO userDTO;
    private String token;
}
