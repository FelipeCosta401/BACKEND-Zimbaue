package com.felipeantunes.Zimbaue.service;

import com.felipeantunes.Zimbaue.infra.security.CorsConfig;
import com.felipeantunes.Zimbaue.infra.security.TokenService;
import com.felipeantunes.Zimbaue.model.dto.AuthenticationDTO;
import com.felipeantunes.Zimbaue.model.dto.LoginDTO;
import com.felipeantunes.Zimbaue.model.dto.LoginResponseDTO;
import com.felipeantunes.Zimbaue.model.dto.UserDTO;
import com.felipeantunes.Zimbaue.model.entity.User;
import com.felipeantunes.Zimbaue.repository.UserRepository;
import com.felipeantunes.Zimbaue.service.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CorsConfig corsConfig;

    @Autowired
    private TokenService tokenService;

    public UserDTO register(AuthenticationDTO userDTO) {
        if(userDTO.getEmail() == null) throw new BadRequestException("Campo email é obrigatório");
        if(userDTO.getName() == null) throw new BadRequestException("Campo de nome é obrigatório");
        if(userDTO.getPassword() == null) throw new BadRequestException("Campo de senha é obrigatório");
        //Verifies if the user already exists
        boolean userExists = this.userRepository.existsByEmail(userDTO.getEmail());
        if(userExists) throw new BadRequestException("Email já cadastrado, escolha outro!");

        String encryptedPassword = corsConfig.passwordEncoder().encode(userDTO.getPassword());
        User userToBeSaved = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(encryptedPassword)
                .role(userDTO.getRole())
                .build();
        User savedUser = this.userRepository.save(userToBeSaved);
        return this.userRepository.getUserById(savedUser.getId()).toUserDTO();
    }

    public LoginResponseDTO login(LoginDTO loginDTO){
        if(loginDTO.getEmail() == null) throw new BadRequestException("Campo email inválido");
        if(loginDTO.getPassword() == null) throw new BadRequestException("Campo senha inválido");

        //Verifies if the user exists
        User user = this.userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new BadRequestException("Credenciais inválidas"));

        //Verifies if the passwords match
        if(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())){
            String token = tokenService.generateToken(user);
            UserDTO userDTO = this.userRepository.getUserById(user.getId()).toUserDTO();
            return LoginResponseDTO.builder()
                    .token(token)
                    .userDTO(userDTO)
                    .build();
        }
        throw new BadRequestException("Credenciais inválidas");
    }
}
