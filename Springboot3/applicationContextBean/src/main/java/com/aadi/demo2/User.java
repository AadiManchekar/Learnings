package com.aadi.demo2;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class User {
    
    String username;
    String password;

}
