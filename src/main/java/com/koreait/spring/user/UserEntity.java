package com.koreait.spring.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {
    private int i_user;
    private String u_id;
    private String u_pw;
    private String u_nm;
    private int gender;
    private String regdt;
    private String profileImg;
}
