package com.example.CRUD.service;

import com.example.CRUD.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO createUser(UserDTO userDTO);
}
