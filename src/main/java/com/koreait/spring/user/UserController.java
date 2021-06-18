package com.koreait.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    //깃허브 구동중
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

    @GetMapping("/logout")
    public String logout(HttpSession hs, HttpServletRequest req){
        hs.invalidate();
        String referer = req.getHeader("Referer");
        return "redirect:" + referer;
        //return "redirect:/user/login";
    }

    @RequestMapping("/join")
    public String join(){ return "user/join"; }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(UserEntity param){
        System.out.println("uid" + param);
        service.join(param);
        return "redirect:/user/login";
    }

    @RequestMapping("/profile")
    public String profile(){
        return "user/profile";
    }

    //@RequestMapping(value = "/profile", method = RequestMethod.POST)
    // 위 아래가 post방식으로 같다.
    @PostMapping("/profile") //@RequestParam("profileImg") jsp의 값을 받아오는 이름과 같기에 생략가능
    public String profile(@RequestParam("profileImg") MultipartFile profileImg){ // MultipartFile[] < 여러파일 옮길때
        return "redirect:" + service.uploadProfile(profileImg);
    }
}
