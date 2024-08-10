package com.management.farm.Controller.userControllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.farm.DTO.userDTOs.UserDto;
import com.management.farm.Model.userModels.User;
import com.management.farm.Service.userServices.UserService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService){
        this.userService = userService;
    }


 
    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto){
        User registeredUser = userService.registerUser(userDto);
        return ResponseEntity.ok(registeredUser);
    }
    
}
