package edu.scau.mis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class MisApplication {
    public static void main(String[] args) {
        //启动Swing界面 OOAD课程使用
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
        SpringApplication.run(MisApplication.class, args);
        System.out.println("MIS开发平台启动成功");
    }
}