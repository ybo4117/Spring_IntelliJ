package com.koreait.spring.user;


import org.apache.commons.io.FilenameUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private HttpSession session;

    public String login(UserEntity param) {
        UserEntity result = mapper.selUser(param);

        if (result == null) { //아이디 없음
            return "redirect:/user/login?err=1";
        } else if (BCrypt.checkpw(param.getU_pw(), result.getU_pw())) { //로그인 성공
            //세션처리
            result.setU_pw(null);
            session.setAttribute("loginUser", result);
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

    public String uploadProfile(MultipartFile img) {
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        final String PATH = "D:/springImg/" + loginUser.getI_user();

        File folder = new File(PATH);
        folder.mkdirs(); // 폴더가 없으면 만들어라는 명령어

        String ext = FilenameUtils.getExtension(img.getOriginalFilename());
        String fileNm = UUID.randomUUID().toString() + "." + ext;

        File target = new File(PATH + "/" + fileNm);
        try {
            img.transferTo(target);

            //이전 이미지 삭제
            File delFile = new File(PATH + "/" + loginUser.getProfileImg());
            if(delFile.exists()){
                delFile.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserEntity param = new UserEntity();
        param.setI_user(loginUser.getI_user());
        param.setProfileImg(fileNm);

        mapper.updUser(param);
        loginUser.setProfileImg(fileNm);

        return "/user/profile";
    }
}
