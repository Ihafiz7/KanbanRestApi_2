package com.example.KanbanBackend.user;

import com.example.KanbanBackend.enums.UpdateBy;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public UserDTO save(@Valid @RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }

    @GetMapping
    public List<UserDTO> getAllUser(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public  UserDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserDTO updateUserById(
            @PathVariable String id,
            @Valid @RequestBody UserDTO userDTO
    ) {
        return userService.updateUser(id, userDTO, UpdateBy.ID);
    }

    @PutMapping("/{email}")
    public UserDTO updateUserByEmail(
            @PathVariable String email,
            @Valid @RequestBody UserDTO userDTO
    ) {
        return userService.updateUser(email, userDTO, UpdateBy.EMAIL);
    }

    @PutMapping("/{userName}")
    public UserDTO updateUserByUserName(
            @PathVariable String userName,
            @Valid @RequestBody UserDTO userDTO
    ) {
        return userService.updateUser(userName, userDTO, UpdateBy.USERNAME);
    }

}
