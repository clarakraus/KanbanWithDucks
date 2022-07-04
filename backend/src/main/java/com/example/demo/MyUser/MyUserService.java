package com.example.demo.MyUser;


import com.example.demo.ToDo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final PasswordEncoder passwordEncoder;
    private final MyUserRepo myUserRepo;

    public void createNewUser(MyUser newUser){
        String hashpassword= passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hashpassword);
        myUserRepo.save(newUser);
    }

    public MyUser findByUserName(String username){
       return myUserRepo.findMyUserByUsername(username).orElseThrow();
    }
    public void findbyUserId (String userId){
        myUserRepo.findMyUserByUserId(userId);
    }

}
