package com.koreait.spring;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "main/home";
    }
    // 주소값이랑 메서드명이랑 같으면 void를 써도됨

}
