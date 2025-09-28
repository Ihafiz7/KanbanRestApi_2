package com.example.KanbanBackend.user;

import com.example.KanbanBackend.enums.UpdateBy;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public  UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //save method
    @Transactional
    public UserDTO save(UserDTO userDTO){
        UserEntity userEntity = userDtoToUserEntity(userDTO);
        return userEntityToUserDto(userRepository.save(userEntity));
    }

    //getAll

    public List<UserDTO> getAllUsers (){
        return userRepository.findAll().stream().map(this::userEntityToUserDto).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id){
        return userRepository.findById(id)
                .map(this::userEntityToUserDto)
                .orElseThrow(() -> new RuntimeException("Id not found: "+ id));
    }

    @Transactional
    public UserDTO updateUser(String identifier, UserDTO userDTO, UpdateBy updateBy) {
        UserEntity existingUser = switch (updateBy) {
            case ID -> {
                Long id = Long.parseLong(identifier);
                yield userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
            }
            case EMAIL -> userRepository.findByEmail(identifier)
                    .orElseThrow(() -> new RuntimeException("User not found with email: " + identifier));
            case USERNAME -> userRepository.findByUserName(identifier)
                    .orElseThrow(() -> new RuntimeException("User not found with username: " + identifier));
            default -> throw new IllegalArgumentException("Invalid update type");
        };
        updateNonNullFields(existingUser, userDTO);

        return userEntityToUserDto(userRepository.save(existingUser));
    }

    private void updateNonNullFields(UserEntity existingUser, UserDTO userDTO) {
        if (userDTO.getUserName() != null) existingUser.setUserName(userDTO.getUserName());
        if (userDTO.getEmail() != null) existingUser.setEmail(userDTO.getEmail());
        if (userDTO.getFirstName() != null) existingUser.setFirstName(userDTO.getFirstName());
        if (userDTO.getLastName() != null) existingUser.setLastName(userDTO.getLastName());
        if (userDTO.getProfilePicture() != null) existingUser.setProfilePicture(userDTO.getProfilePicture());
        if (userDTO.getPasswordHash() != null) existingUser.setPasswordHash(userDTO.getPasswordHash());
    }

    //Entity To DTO
    public UserDTO userEntityToUserDto(UserEntity userEntity){
        if (userEntity == null) return null;
        UserDTO userDTO = new UserDTO();

        userDTO.setId((userEntity.getId()));
        userDTO.setEmail((userEntity.getEmail()));
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setProfilePicture(userEntity.getProfilePicture());
        userDTO.setPasswordHash(userEntity.getPasswordHash());
        return userDTO;
    }

    //Dto to entity
    public UserEntity userDtoToUserEntity(UserDTO userDTO){
        if (userDTO == null) return null;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setProfilePicture(userDTO.getProfilePicture());
        userEntity.setPasswordHash(userDTO.getPasswordHash());
        return userEntity;
    }
}
