package com.koreait.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/login")
    public String login(@RequestParam(value = "err", defaultValue = "0") int err, Model model, HttpServletRequest request){
        System.out.println("err : " + err);
        switch (err){
            case 1:
                model.addAttribute("errMsg", "아이디를 확인해 주세요");
                break;
            case 2:
                model.addAttribute("errMsg", "비밀번호를 확인해 주세요");
                break;
        }
        return "user/login";
    }



    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(UserEntity param){
        System.out.println("얘는 컨트롤러 :" + param.getU_pw());
        return service.login(param);

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
