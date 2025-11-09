package org.example.testareonline.service.implementation;

import lombok.AllArgsConstructor;
import org.example.testareonline.dto.request.UserRequest;
import org.example.testareonline.dto.response.UserDTO;
import org.example.testareonline.entity.User;
import org.example.testareonline.mapper.UserMapper;
import org.example.testareonline.repository.UserRepository;
import org.example.testareonline.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDTO createUser(UserRequest request) {
        User user = userMapper.toEntity(request);
        user.setParola(passwordEncoder.encode(user.getParola()));
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO updateUser(Integer id, UserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(request.username());
        user.setParola(request.parola());
        user.setRol(request.rol());

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
