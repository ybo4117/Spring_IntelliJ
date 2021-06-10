package com.koreait.spring.board;

import com.koreait.spring.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardMapper mapper;

    @Autowired
    private BoardCmtMapper cmtMapper;

    @Autowired
    private MyUtils myUtils;

    public List<BoardDomain> selBoardList(){
        return mapper.selBoardList();
    }

    public BoardDomain selBoard(BoardDTO param){
        return mapper.selBoard(param);
    }

    // return값은 iboard값
    public int writeMod(BoardEntity param){
        param.setI_user(myUtils.getLoginUserPk());

        if(param.getIboard() == 0){ //등록
            mapper.insBoard(param);
        } else{ //수정
            mapper.updBoard(param);
        }

        return param.getIboard();
    }

    public int delBoard(BoardEntity param){
        // 댓글 먼저 삭제
        BoardCmtEntity cmtParam = new BoardCmtEntity();
        cmtParam.setIboard(param.getIboard());
        cmtMapper.delBoardCmt(cmtParam);

        // 이후 게시글 삭제
        param.setI_user(myUtils.getLoginUserPk());
        return mapper.delBoard(param);
    }


    // 댓글 부분
    public int insBoardCmt(BoardCmtEntity param){
        param.setI_user(myUtils.getLoginUserPk());
        return cmtMapper.insBoardCmt(param);
    }

    public List<BoardCmtDomain> selBoardCmtList(BoardCmtEntity param){
        return cmtMapper.selBoardCmtList(param);
    }

    public int updBoardCmt(BoardCmtEntity param){
        param.setI_user(myUtils.getLoginUserPk());
        return cmtMapper.updBoardCmt(param);
    }

    public int delBoardCmt(BoardCmtEntity param){
        param.setI_user(myUtils.getLoginUserPk());
        return cmtMapper.delBoardCmt(param);
    }

}
