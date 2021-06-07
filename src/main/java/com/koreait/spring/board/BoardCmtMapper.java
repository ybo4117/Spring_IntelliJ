package com.koreait.spring.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCmtMapper {
    int insBoardCmt(BoardCmtEntity param);

    List<BoardCmtDomain> selBoardCmtList(BoardCmtEntity param);
    //여러줄을 가져오기때문에 List를 붙힌다.
}
// select 제외하고 나머지 int del upd 는 리턴타입이 ins, void 줘도 상관없음
