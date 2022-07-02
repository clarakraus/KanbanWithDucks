package com.example.demo.MyUser;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


@RequiredArgsConstructor
@Component
public class MongoUserDetailsService implements UserDetailsService {

    private final MyUserRepo myUserRepo;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return myUserRepo.findMyUsersByUsername(username)
                .map(myUser -> new User(myUser.getUsername(), myUser.getPassword(), List.of(new SimpleGrantedAuthority("user"))))
                .orElseThrow();
    }
}
