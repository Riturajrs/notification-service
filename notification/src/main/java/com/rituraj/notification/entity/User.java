package com.rituraj.notification.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "USER_ENTITY")
public class User {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NonNull
    @Column(unique = true)
    @Email
    private String email;

    @NonNull
    private String password;

    private Long phoneNumber;

    private boolean isMailVerified = false;
}
