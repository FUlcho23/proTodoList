package org.big.controller;

import java.util.List;

import org.big.dto.BoardDto;
import org.big.dto.MemberDto;
import org.big.mapper.proTodoMapper;
import org.big.service.ProTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;


@Controller
public class ProTodoController {
	 
	@Autowired
	   private ProTodoService ProTodoService;
	
	@Autowired
	private proTodoMapper todoMapper;

	@RequestMapping("/main/todo")
	 public String todo(HttpSession session)throws Exception{
		
		return "board/todo.html";
	}
	
	@RequestMapping("/home")
	 public String main(HttpSession session)throws Exception{
		 
	    return "board/main.html";
	}
	@RequestMapping("/main/board")
	 public ModelAndView board()throws Exception{
		ModelAndView mv = new ModelAndView("board/boardList.html");
		
		List<BoardDto> list = ProTodoService.selectBoardList();
		mv.addObject("list",list);
		
		return mv;
	}
	@RequestMapping("/main/board/detail")
	 public String detail()throws Exception{
		return "board/boardDetail.html";
	}
	@RequestMapping("/main/board/write")
	 public String write()throws Exception{
		return "board/boardWrite.html";
	}
	
	@RequestMapping("/main/team")
	 public String team()throws Exception{
		return "board/team.html";
	}
	@RequestMapping("/main/mypage")
	 public String mypage()throws Exception{
		return "board/mypage.html";
	}
	@RequestMapping("/main/login")
	 public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "아이디와 비밀번호를 확인해 주세요");
        }
        return "board/login.html"; 
    }
	@PostMapping("/main/login")
    public String loginProcess(@RequestParam String id, @RequestParam String pwd,HttpSession session, RedirectAttributes redirectAttributes) {
		
		// ✅ DB에서 사용자 조회
	    MemberDto memberId = todoMapper.loginFindMember(id);

	    if (memberId == null || !memberId.getMPassword().equals(pwd)) {
	        redirectAttributes.addAttribute("error", "1");
	        return "redirect:/main/login"; // 로그인 실패 시 다시 로그인 페이지로 이동
	    }
        
        // ✅ 로그인 성공 시 세션에 사용자 ID 저장
        session.setAttribute("memberId", memberId.getMemberId());

        return "redirect:/home"; // 로그인 성공 시 홈 화면으로 이동
    }
	
	@GetMapping("/logout")
    public String logout(HttpSession session) {
        // ✅ 로그아웃 시 세션 삭제
		session.invalidate();
        return "redirect:/home";
    }
	@RequestMapping("/login/memberadd")
	 public String memberaddhome()throws Exception{
		return "board/memberadd.html";
	}
	@PostMapping("/login/memberadd")
	public String memberadd(MemberDto member) throws Exception{
		ProTodoService.memberAdd(member);
		return "redirect:/main/login";
	}
}
