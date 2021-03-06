package com.koreait.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Controller에 RequestMapping이 있어야한다 Spring 구조
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

    @RequestMapping("/list")
    public String list(BoardDTO param, Model model){
        List<BoardDomain> list =  service.selBoardList(param);
        model.addAttribute("list",list);
        model.addAttribute("maxPageVal", service.selMaxpageVal(param));
        return "board/list";
    }

    @RequestMapping("/detail")
    public String detail(BoardDTO param, Model model){
        System.out.println("iboard : " + param.getIboard());
        BoardDomain data = service.selBoard(param);
        model.addAttribute(data);
        return "board/detail";
    }

    @GetMapping("/writeMod")
    public void writeMod(BoardDTO param, Model model){
        System.out.println("param = " + param.getIboard());
        if(param.getIboard() > 0) {
            model.addAttribute("data", service.selBoard(param));
        }
    }

    @PostMapping("/writeMod")
    public String writeModProc(BoardEntity param){
        int iboard = service.writeMod(param);
        return "redirect:detail?iboard=" + iboard;

    }

    @GetMapping("/delBoard")
    public String delBoard(BoardEntity param){
        service.delBoard(param);
        return "redirect:list";
    }


    //댓글 부분
    @ResponseBody
    @RequestMapping(value = "/cmt", method = RequestMethod.POST)
    public Map<String, Integer> cmtIns(@RequestBody BoardCmtEntity param){
        System.out.println("param = " + param);

        int result = service.insBoardCmt(param);

        Map<String, Integer> data = new HashMap<>();
        data.put("result", result);

        return data;
    }

    @ResponseBody
    @RequestMapping("/cmt/{iboard}")
    public List<BoardCmtDomain> cmtSel(BoardCmtEntity param ,@PathVariable("iboard") int iboard){
        param.setIboard(iboard);
        return service.selBoardCmtList(param);
    }

    @ResponseBody
    @RequestMapping(value = "/cmt",method = RequestMethod.PUT)
    public Map<String, Integer> cmtUpd(@RequestBody BoardCmtEntity param){
        int result = service.updBoardCmt(param);
        Map<String, Integer> data = new HashMap<>();
        data.put("result",result);

        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/cmt/{icmt}", method = RequestMethod.DELETE)
    public Map<String, Integer> cmtDel(BoardCmtEntity param,@PathVariable("icmt") int icmt){

        int result = service.delBoardCmt(param);
        Map<String, Integer> data = new HashMap<>();
        data.put("result",result);

        return data;
    }

    @GetMapping("/favList")
    public void favList(){

    }


}
