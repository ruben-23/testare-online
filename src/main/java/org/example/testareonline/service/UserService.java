package org.example.testareonline.service;

import org.example.testareonline.dto.request.UserRequest;
import org.example.testareonline.dto.response.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserRequest request);
    UserDTO getUserById(Integer id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Integer id, UserRequest request);
    void deleteUser(Integer id);
}
