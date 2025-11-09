package org.example.testareonline.controller;

import lombok.AllArgsConstructor;
import org.example.testareonline.dto.request.LoginRequest;
import org.example.testareonline.dto.response.JwtResponse;
import org.example.testareonline.dto.response.UserDTO;
import org.example.testareonline.entity.User;
import org.example.testareonline.mapper.UserMapper;
import org.example.testareonline.repository.UserRepository;
import org.example.testareonline.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.parola()
                )
        );

        User user = userRepository.findByUsername(loginRequest.username()).orElseThrow();
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/validate")
    public boolean validateToken(@RequestHeader("Authorization") String token) {
        String finalToken = token.replace("Bearer ", "");
        return jwtService.validateToken(finalToken);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = (Integer) auth.getPrincipal();

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userMapper.toDTO(user));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
