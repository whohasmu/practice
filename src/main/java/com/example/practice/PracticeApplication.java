package com.example.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
//        test();
    }

    public void test(){
        String temp = new BCryptPasswordEncoder().encode("1234");
        System.out.println("new BCryptPasswordEncoder : " + temp);

    }

}
