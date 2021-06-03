package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDomain extends BoardEntity{
    private String WriterNm;
    private String profileImg;
}
