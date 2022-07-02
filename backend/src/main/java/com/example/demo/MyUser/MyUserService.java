package com.example.demo.MyUser;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

}
