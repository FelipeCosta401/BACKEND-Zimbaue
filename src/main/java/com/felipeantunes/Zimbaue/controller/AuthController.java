package com.felipeantunes.Zimbaue.controller;

import com.felipeantunes.Zimbaue.model.dto.AuthenticationDTO;
import com.felipeantunes.Zimbaue.model.dto.LoginDTO;
import com.felipeantunes.Zimbaue.model.dto.LoginResponseDTO;
import com.felipeantunes.Zimbaue.model.dto.UserDTO;
import com.felipeantunes.Zimbaue.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody AuthenticationDTO userDTO){
        UserDTO createdUser = this.authService.register(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO){
        LoginResponseDTO loggedUser = this.authService.login(loginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(loggedUser);
    }
}
