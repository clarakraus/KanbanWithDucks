package com.example.demo.MyUser;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class MyUserController {

    private final MyUserService myUserService;

    @PostMapping
    public void registerUser(@RequestBody MyUser newuser){
        myUserService.createNewUser(newuser);


    }



}
