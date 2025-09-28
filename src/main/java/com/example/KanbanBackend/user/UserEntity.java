package com.example.KanbanBackend.user;

import com.example.KanbanBackend.enums.UserRole;
import com.example.KanbanBackend.enums.UserStatus;
import com.example.KanbanBackend.persistance.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)

public class UserEntity extends BaseEntity {
    @Column(name = "user_name",unique = true, nullable = false)
    @Size(min = 5 ,max = 50)
    private  String userName;

    @Column(name = "email",unique = true,nullable = false,length = 50)
    @Email
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "first_name")
    @Size(min = 3 ,max = 50)
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 3 ,max = 50)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "notifications", columnDefinition = "Text")
    private String notifications;





}
