package edu.scau.mis.web.controller.system;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    //
    @GetMapping("/")
    public String index(){
        return "Hello SpringBoot!";
    }


    @PreAuthorize("hasAuthority('system:user:list')")
    @GetMapping("/test1")
    public String test1(){
        return "授权【管理员】访问";
    }

    @PreAuthorize("hasAuthority('pos:sale:add')")
    @GetMapping("/test2")
    public String test2(){
        return "授权【收银员】访问";
    }
}
