package org.big.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ProTodoController {
	@RequestMapping("/")
	 public String hello()throws Exception{
		return "board/test.html";
	}
}
