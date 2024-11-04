package com.felipeantunes.Zimbaue.controller;

import com.felipeantunes.Zimbaue.model.dto.UserDTO;
import com.felipeantunes.Zimbaue.model.entity.User;
import com.felipeantunes.Zimbaue.service.UserService;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUserList(){
        List<UserDTO> userDTOList = this.userService.getUserList();
        return ResponseEntity.status(HttpStatus.OK).body(userDTOList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId){
        UserDTO userDto = this.userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User createdUser = this.userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
