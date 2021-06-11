package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private int iboard;
    private int i_user;
    private int selType; // 0 : 기본리스트, 1 : 좋아요리스트 , ~~등등
    private int page = 1;
    private int startIdx;
    private int recordCnt = 5;
    private int searchType;
    private String searchText;
}
