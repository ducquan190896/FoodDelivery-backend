package com.fooddelivery.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.backend.Mapper.UserMapper;
import com.fooddelivery.backend.Models.Users;
import com.fooddelivery.backend.Models.Request.UserSignIn;
import com.fooddelivery.backend.Models.Request.UserSignUp;
import com.fooddelivery.backend.Models.Response.UserResponse;
import com.fooddelivery.backend.Service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getByUsername(@PathVariable String username) {
        return new ResponseEntity<UserResponse>(userService.getUseResByUsername(username), HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponse> getByid(@PathVariable long id) {
        return new ResponseEntity<UserResponse>(userService.getUserResById(id), HttpStatus.OK);
    }
    @PutMapping("/signIn")
    public ResponseEntity<UserResponse> signIn(@Valid @RequestBody UserSignIn userSignIn) {
        return new ResponseEntity<UserResponse>(userService.signIn(userSignIn), HttpStatus.OK);
    }
    //requires token
    @GetMapping("/authUser/getAuthUser")
    public ResponseEntity<UserResponse> getAuthUser() {
        return new ResponseEntity<UserResponse>(userService.loadAuthUserRes(), HttpStatus.OK);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserSignUp userSignup) {
        return new ResponseEntity<UserResponse>(userService.saveUser(userSignup), HttpStatus.CREATED);
    }

   
}