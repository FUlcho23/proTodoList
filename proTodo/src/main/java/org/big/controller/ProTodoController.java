package org.big.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ProTodoController {
<<<<<<< HEAD
	@RequestMapping("/main/todo")
	 public String todo()throws Exception{
		return "board/test.html";
	}
	
	@RequestMapping("/main")
	 public String main()throws Exception{
		return "board/main.html";
	}
	@RequestMapping("/main/board")
	 public String board()throws Exception{
		//안녕 이 안에 값(리스트) 받아와야 동작해~DB에서!
		return "board/boardList.html";
	}
	@RequestMapping("/main/team")
	 public String team()throws Exception{
		return "board/team.html";
	}
	@RequestMapping("/main/mypage")
	 public String mypage()throws Exception{
		return "board/mypage.html";
=======
	@RequestMapping("/")
	 public String hello()throws Exception{
		return "board/test.html";
>>>>>>> refs/remotes/origin/master
	}
}
