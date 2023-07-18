package edu.scau.mis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MisApplication.class, args);
        System.out.println("WROMS后台启动成功");
    }
}
