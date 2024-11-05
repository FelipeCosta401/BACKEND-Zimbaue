package com.felipeantunes.Zimbaue.model.dto;

import com.felipeantunes.Zimbaue.model.entity.Event;
import com.felipeantunes.Zimbaue.model.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private Integer id;

    private String title;

    private String description;

    private LocalDateTime date;

    private LocalDateTime createdAt;

    private UserDTO userDTO;

    public static Event toEventEntity(EventDTO eventDTO){
        User user = UserDTO.toUserEntity(eventDTO.getUserDTO());
        return Event.builder()
                .id(eventDTO.getId())
                .title(eventDTO.getTitle())
                .description(eventDTO.getDescription())
                .date(eventDTO.getDate())
                .createdAt(eventDTO.getCreatedAt())
                .user(user)
                .build();
    }
}
