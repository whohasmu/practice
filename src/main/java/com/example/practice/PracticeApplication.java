package com.example.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class PracticeApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(PracticeApplication.class, args);
        test2();

    }

    public void test(){
        String temp = new BCryptPasswordEncoder().encode("1234");
        System.out.println("new BCryptPasswordEncoder : " + temp);

    }

    public static void test2() throws IOException {
        FileReader reader = new FileReader("D:\\정산관리 분석.txt");

        int ch;
        while ((ch = reader.read()) != -1) {
            System.out.print((char) ch);
        }

    }


}
