package com.example.demo.MyUser;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface MyUserRepo extends MongoRepository<MyUser, String> {

        Optional<MyUser> findMyUserByUsername(String userName);
        Optional<MyUser> findMyUserByUserId(String userId);



}
