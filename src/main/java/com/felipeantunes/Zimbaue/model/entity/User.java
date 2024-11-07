package com.felipeantunes.Zimbaue.model.entity;

import com.felipeantunes.Zimbaue.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private Role role;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }

}