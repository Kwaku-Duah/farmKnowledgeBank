package com.management.farm.Service.userServices;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.management.farm.Exception.UserExceptions.EmailAlreadyInUseException;

import com.management.farm.DTO.userDTOs.UserDto;
import com.management.farm.Model.userModels.User;
import com.management.farm.Repository.userRepositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public User registerUser(UserDto userDto){
        if (userRepository.existsByEmail(userDto.getEmail())){
            throw new EmailAlreadyInUseException("Email already in use!");
        }


        User user = new User();

        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(user);
    }
    

    public User loginUser(String email, String password) throws Exception {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            } else {
                throw new Exception("Invalid credentials");
            } 
            
        } else {
            throw new Exception("User not found");
        }
    }

}
