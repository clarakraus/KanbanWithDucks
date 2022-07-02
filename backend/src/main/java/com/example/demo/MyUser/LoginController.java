package com.example.demo.MyUser;


import com.example.demo.Security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDetails loginDetails) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDetails.getUsername(), loginDetails.getPassword()));
            return ResponseEntity.ok(new LoginResponse(jwtService.createToken(new HashMap<>(), loginDetails.getUsername())));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

}
