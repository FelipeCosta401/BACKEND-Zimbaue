package com.felipeantunes.Zimbaue.interfaces;

import com.felipeantunes.Zimbaue.model.dto.PostDTO;
import com.felipeantunes.Zimbaue.model.dto.UserDTO;
import com.felipeantunes.Zimbaue.model.enums.Role;

import java.time.LocalDateTime;

public interface IPostDTO {

    //Post's data
    public Integer getPostId();
    public String getPostTitle();
    public String getPostDescription();
    public String getPostLink();
    public LocalDateTime getPostDate();

    //User's data
    public Integer getUserId();
    public String getUserName();
    public String getUserEmail();
    public Role GetUserRole();

    public default PostDTO toPostDTO(){
        return PostDTO.builder()
                .id(getPostId())
                .title(getPostTitle())
                .description(getPostDescription())
                .link(getPostLink())
                .createdAt(getPostDate())
                .userDTO(UserDTO.builder()
                        .id(getUserId())
                        .name(getUserName())
                        .email(getUserEmail())
                        .build())
                .build();
    }

}
