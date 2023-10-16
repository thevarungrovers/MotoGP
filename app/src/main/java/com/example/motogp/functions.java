package com.example.motogp;

public class functions {
    public static String getUsername(String email){
        String[] usernameArr = email.toLowerCase().split("@");
        String username = usernameArr[0];

        return username;
    }
}
