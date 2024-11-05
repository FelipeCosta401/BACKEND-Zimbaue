package com.felipeantunes.Zimbaue.interfaces;

import com.felipeantunes.Zimbaue.model.dto.EventDTO;
import com.felipeantunes.Zimbaue.model.dto.UserDTO;

import java.time.LocalDateTime;

public interface IEventDTO {

    //Event's data
    public Integer getEventId();
    public String getEventTitle();
    public String getEventDescription();
    public LocalDateTime getEventDate();
    public LocalDateTime getEventCreatedAt();

    //User's data
    public Integer getUserId();
    public String getUserName();
    public String getUserEmail();
    public LocalDateTime getUserCreatedAt();

    public default EventDTO toEventDTO(){
        return EventDTO.builder()
                .id(getEventId())
                .title(getEventTitle())
                .description(getEventDescription())
                .date(getEventDate())
                .createdAt(getEventCreatedAt())
                .userDTO(UserDTO.builder()
                        .id(getUserId())
                        .name(getUserName())
                        .email(getUserEmail())
                        .createdAt(getUserCreatedAt())
                        .build())
                .build();
    }
}
