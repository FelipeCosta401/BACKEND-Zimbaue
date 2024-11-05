package com.felipeantunes.Zimbaue.interfaces;

import com.felipeantunes.Zimbaue.model.dto.EnrollmentUserEventDTO;
import com.felipeantunes.Zimbaue.model.dto.EventDTO;
import com.felipeantunes.Zimbaue.model.dto.UserDTO;

import java.time.LocalDateTime;

public interface IEnrollmentDTO {
    //Enrollment's data
    public Integer getEnrollmentId();
    public Integer getEnrollmentStatus();
    public LocalDateTime getEnrollmentDate();

    //User's data
    public Integer getUserId();
    public String getUserName();
    public String getUserEmail();

    //Event's data
    public Integer getEventId();
    public String getEventTitle();
    public String getEventDescription();
    public LocalDateTime getEventDate();

    public default EnrollmentUserEventDTO toEnrollmentDTO(){
        return EnrollmentUserEventDTO.builder()
                .id(getEnrollmentId())
                .status(getEnrollmentStatus())
                .createdAt(getEnrollmentDate())
                .userDTO(UserDTO.builder()
                        .id(getUserId())
                        .name(getUserName())
                        .email(getUserEmail())
                        .build())
                .eventDTO(EventDTO.builder()
                        .id(getEventId())
                        .title(getEventTitle())
                        .description(getEventDescription())
                        .date(getEventDate())
                        .build())
                .build();
    }

}
