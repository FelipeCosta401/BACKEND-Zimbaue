package com.felipeantunes.Zimbaue.model.dto;

import com.felipeantunes.Zimbaue.model.entity.EnrollmentUserEvent;
import com.felipeantunes.Zimbaue.model.entity.Event;
import com.felipeantunes.Zimbaue.model.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentUserEventDTO {
    private Integer id;
    private Integer status;
    private LocalDateTime createdAt;

    private UserDTO userDTO;

    private EventDTO eventDTO;

    public static EnrollmentUserEvent toEnrollmentEntity(EnrollmentUserEventDTO enrollmentDTO){
        User user = UserDTO.toUserEntity(enrollmentDTO.getUserDTO());
        Event event = EventDTO.toEventEntity(enrollmentDTO.getEventDTO());

        return EnrollmentUserEvent.builder()
                .status(enrollmentDTO.getStatus())
                .user(user)
                .event(event)
                .createdAt(enrollmentDTO.getCreatedAt())
                .build();
    }

}
