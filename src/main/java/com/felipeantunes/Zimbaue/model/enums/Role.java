package com.felipeantunes.Zimbaue.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN(0, "admin"),
    USER(1, "user");

    @JsonValue
    private Integer code;
    private String description;

    public Role getByCode(Integer code){
        for(Role e: values()){
            if(e.getCode().equals(code)){
                return e;
            }
        }
        return null;
    }
}