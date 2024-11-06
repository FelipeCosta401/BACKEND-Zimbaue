package com.felipeantunes.Zimbaue.interfaces;

import com.felipeantunes.Zimbaue.model.dto.UserDTO;
import com.felipeantunes.Zimbaue.model.enums.Role;

import java.time.LocalDateTime;

public interface IUserDTO {
    public Integer getId();

    public String getName();

    public String getEmail();

    public LocalDateTime getCreatedAt();

    public Integer getRole();

    public default UserDTO toUserDTO(){
        return UserDTO.builder()
                .id(getId())
                .name(getName())
                .email(getEmail())
                .createdAt(getCreatedAt())
                .role(Role.getByCode(getRole()))
                .build();
    }

}
