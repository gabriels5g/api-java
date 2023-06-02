package com.example.refreshtoken.controllers;

import com.example.refreshtoken.dto.UserDTO;
import com.example.refreshtoken.models.UserModel;
import com.example.refreshtoken.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {
    final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping()
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDTO userDTO) {
        if (userService.existsByEmail(userDTO.getEmail())) {
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("conflict: email has already been registered");
        }

        var user = new UserModel();
        BeanUtils.copyProperties(userDTO, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @GetMapping()
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }
}
