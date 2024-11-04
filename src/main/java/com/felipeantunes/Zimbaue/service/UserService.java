package com.felipeantunes.Zimbaue.service;

import com.felipeantunes.Zimbaue.interfaces.IUserDTO;
import com.felipeantunes.Zimbaue.model.dto.UserDTO;
import com.felipeantunes.Zimbaue.model.entity.User;
import com.felipeantunes.Zimbaue.repository.UserRepository;
import com.felipeantunes.Zimbaue.service.exceptions.BadRequestException;
import com.felipeantunes.Zimbaue.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getUserList(){
        List<IUserDTO> iUserDTOList = this.userRepository.getUserList();
        ArrayList<UserDTO> userDTOList = new ArrayList<>();

        for(IUserDTO iUserDto : iUserDTOList){
            userDTOList.add(iUserDto.toUserDTO());
        }

        return userDTOList;
    }

    public UserDTO getUserById(Integer id){
        //Verifies if there is any user with the id
        boolean userExists = this.userRepository.existsById(id);
        if(!userExists){
            throw new NotFoundException("Usuário não encontrado com esse id");
        }
        //If so, return userDTO
        IUserDTO iUserDTO = this.userRepository.getUserById(id);
        return iUserDTO.toUserDTO();
    }

    public User save(User user) {
        //Verifies if there are any mandatory field empty
        if(user.getName() == null){
            throw new BadRequestException("Campo nome é obrigatório");
        } else if(user.getEmail() == null){
            throw new BadRequestException("Campo email é obrigatório");
        } else if(user.getPassword() == null){
            throw new BadRequestException("Campo senha é obrigatório");
        }

        //Verifies if the email is available
        boolean emailAlreadyTaken = userRepository.existsByEmail(user.getEmail());
        if(emailAlreadyTaken){
            throw new BadRequestException("Esse email já foi cadastrado, por favor escolha outro!");
        }
        return this.userRepository.save(user);
    }
}
