package com.felipeantunes.Zimbaue.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;

@Entity
@Table(name = "enrollment_user_event")
@Builder
@Getter
@Setter
public class EnrollmentUserEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "event_id")
    @ManyToOne
    private Event event;

}
