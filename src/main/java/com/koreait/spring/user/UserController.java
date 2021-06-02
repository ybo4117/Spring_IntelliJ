package com.koreait.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/login")
    public String login(){
        return "user/login";
    }


    @RequestMapping("/join")
    public String join(){ return "user/join"; }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(UserEntity param){
        System.out.println("uid" + param);
        service.join(param);

        return "redirect:/user/login";
    }
}
