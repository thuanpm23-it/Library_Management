package com.example.service;


import java.util.List;

import com.example.dto.UserDto;
import com.example.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();

	List<User> getAllUsers();
}