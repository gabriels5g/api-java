package com.example.refreshtoken.controllers;

import com.example.refreshtoken.dto.UserDTO;
import com.example.refreshtoken.models.UserModel;
import com.example.refreshtoken.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


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
    public ResponseEntity<Page<UserModel>> getAllUsers(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id")UUID id) {
        Optional<UserModel> userModelOptional = userService.findById(id);

            if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id")UUID id) {
        Optional<UserModel> userModelOptional = userService.findById(id);

        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userService.delete(userModelOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("User Spot deleted Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id")UUID id, @RequestBody @Valid UserDTO userDTO) {
        Optional<UserModel> userModelOptional = userService.findById(id);

        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);
        userModel.setId(userModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
    }
}
