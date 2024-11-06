package com.felipeantunes.Zimbaue.controller;

import com.felipeantunes.Zimbaue.model.dto.AuthenticationDTO;
import com.felipeantunes.Zimbaue.model.dto.LoginResponseDTO;
import com.felipeantunes.Zimbaue.model.dto.UserDTO;
import com.felipeantunes.Zimbaue.model.entity.User;
import com.felipeantunes.Zimbaue.service.AuthorizationService;
import com.felipeantunes.Zimbaue.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthorizationService service;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO credentials){
        var usernamePassword = new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        User user = (User) auth.getPrincipal();

        LoginResponseDTO loggedUser = LoginResponseDTO.builder()
                .userDTO(UserDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .createdAt(user.getCreatedAt())
                        .build())
                .token(token)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(loggedUser);
    }

    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<UserDTO> register(@RequestBody User user){
        UserDTO newUser = this.service.saveNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }


}
