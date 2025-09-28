package com.example.KanbanBackend.user;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String passwordHash;

}
