package com.koreait.spring.user;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    public String login(UserEntity param) {
        UserEntity result=mapper.selUser(param);

        if(result == null) { //아이디 없음
            return "redirect:/user/login?err=1";
        } else if(BCrypt.checkpw(param.getU_pw(), result.getU_pw())) { //로그인 성공
            return "redirect:/board/list";
        } else { //TODO: 비밀번호 틀림
            return "redirect:/user/login?err=2";
        }

    }

    public int join(UserEntity param) {
        String cryptPW = BCrypt.hashpw(param.getU_pw(), BCrypt.gensalt());
        param.setU_pw(cryptPW);
        return mapper.insUser(param);
    }
}
