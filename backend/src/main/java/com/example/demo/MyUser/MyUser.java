package com.example.demo.MyUser;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class MyUser {

    @Id
    private String userId;
    @Indexed(unique = true)
    private String username;
    private String password;

    public MyUser( String username, String password){
        this.username= username;
        this.password= password;
    }
}
