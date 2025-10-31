package com.example.CRUD.service;

import com.example.CRUD.dto.UserDTO;
import com.example.CRUD.model.User;
import com.example.CRUD.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        // TODO: Hash password
        return convertToDTO(userRepository.save(user));
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setRole(userDTO.getRole());
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}
