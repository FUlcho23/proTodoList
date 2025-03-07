package org.big.controller;

import org.big.dao.BbsDao;
import org.big.dto.BbsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;

@Controller
public class BbsController {

    @Autowired
    private BbsDao bbsDao;

    private static final int LISTCOUNT = 5;

    // Board List
    @GetMapping("/bookmarket/BoardListAction.do")
    public String requestBoardList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                   @RequestParam(value = "items", required = false) String items,
                                   @RequestParam(value = "text", required = false) String text,
                                   Model model) {
        ArrayList<BbsDto> boardlist;

        int total_record = bbsDao.getListCount(items, text);
        boardlist = bbsDao.getBoardList(pageNum, LISTCOUNT, items, text);

        int total_page = (int) Math.ceil((double) total_record / LISTCOUNT);

        model.addAttribute("pageNum", pageNum);
        model.addAttribute("total_page", total_page);
        model.addAttribute("total_record", total_record);
        model.addAttribute("boardlist", boardlist);

        return "board/list";
    }

    // Write Form
    @GetMapping("/bookmarket/BoardWriteForm.do")
    public String requestLoginName(@RequestParam("id") String id, Model model, HttpSession session) {
        String name = bbsDao.getLoginNameById(id);
        session.setAttribute("sessionId", id);  // 세션에 id 저장
        model.addAttribute("name", name);
        return "board/writeForm";
    }


    // Write Action
    @PostMapping("/bookmarket/BoardWriteAction.do")
    public String requestBoardWrite(@RequestParam("id") String id,
                                    @RequestParam("name") String name,
                                    @RequestParam("subject") String subject,
                                    @RequestParam("content") String content,
                                    HttpServletRequest request) {

        // IP 값은 request.getRemoteAddr()으로 얻을 수 있음
        String ip = request.getRemoteAddr(); // 또는 IP를 다른 방식으로 처리할 수도 있음

        BbsDto board = new BbsDto();
        board.setId(id);
        board.setName(name);
        board.setSubject(subject);
        board.setContent(content);
        board.setHit(0);
        board.setRegist_day(new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)").format(new java.util.Date()));
        board.setIp(ip);
        bbsDao.insertBoard(board);

        return "redirect:/bookmarket/BoardListAction.do";
    }



    // View Action
    @GetMapping("/bookmarket/BoardViewAction.do")
    public String requestBoardView(@RequestParam("num") int num,
                                   @RequestParam("pageNum") int pageNum,
                                   Model model) {
        BbsDto board = bbsDao.getBoardByNum(num, pageNum);
        model.addAttribute("num", num);
        model.addAttribute("page", pageNum);
        model.addAttribute("board", board);

        return "board/view";
    }

    // Update Action
    @PostMapping("/bookmarket/BoardUpdateAction.do")
    public String requestBoardUpdate(@RequestParam("num") int num,
                                     @RequestParam("name") String name,
                                     @RequestParam("subject") String subject,
                                     @RequestParam("content") String content,
                                     HttpServletRequest request) {  // HttpServletRequest 추가

        // IP 값은 request.getRemoteAddr()으로 얻을 수 있음
        String ip = request.getRemoteAddr(); // 또는 IP를 다른 방식으로 처리할 수도 있음

        BbsDto board = new BbsDto();
        board.setNum(num);
        board.setName(name);
        board.setSubject(subject);
        board.setContent(content);
        board.setHit(0);
        board.setRegist_day(new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)").format(new java.util.Date()));
        board.setIp(ip);  // IP 값을 설정
        bbsDao.updateBoard(board);

        return "redirect:/bookmarket/BoardListAction.do";
    }

    // Delete Action
    @GetMapping("/bookmarket/BoardDeleteAction.do")
    public String requestBoardDelete(@RequestParam("num") int num) {
        bbsDao.deleteBoard(num);
        return "redirect:/bookmarket/BoardListAction.do";
    }
}
