package com.felipeantunes.Zimbaue.service;

import com.felipeantunes.Zimbaue.interfaces.IUserDTO;
import com.felipeantunes.Zimbaue.model.dto.AuthenticationDTO;
import com.felipeantunes.Zimbaue.model.dto.LoginResponseDTO;
import com.felipeantunes.Zimbaue.model.dto.UserDTO;
import com.felipeantunes.Zimbaue.model.entity.User;
import com.felipeantunes.Zimbaue.repository.UserRepository;
import com.felipeantunes.Zimbaue.service.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public UserDTO saveNewUser(User user){

        //Validations
        if(user.getUsername() == null){
            throw new BadRequestException("Campo nome obrigatorio");
        } else if(user.getPassword().length() < 8){
            throw new BadRequestException("Senha muito fraca, escolha uma mais forte!");
        } else if(this.userRepository.findByEmail(user.getEmail()) != null){
            throw new BadRequestException("Email ja cadastrado");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        User newUser = User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(encryptedPassword)
                .role(user.getRole())
                .build();
        User createdUser = this.userRepository.save(newUser);
        IUserDTO iUserDTO = this.userRepository.getUserById(createdUser.getId());
        return iUserDTO.toUserDTO();
    }

}
