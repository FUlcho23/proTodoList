package org.big.controller;

import java.util.List;

import org.big.dto.BoardDto;
import org.big.dto.MemberDto;
import org.big.dto.TeamDto;
import org.big.mapper.proTodoMapper;
import org.big.service.ProTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	
	  @RequestMapping("/main/todo") public String todo(HttpSession session)throws Exception{
		  return "board/todo.html"; 
		  }
	 
	
	@RequestMapping("/home")
	 public String main(HttpSession session)throws Exception{
		 
	    return "board/main.html";
	}
	//====================================================================board
	@RequestMapping("/main/board")
	 public ModelAndView board()throws Exception{
		ModelAndView mv = new ModelAndView("board/boardList.html");
		
		List<BoardDto> list = ProTodoService.selectBoardList();
		mv.addObject("list",list);
		
		return mv;
	}
	@RequestMapping("/main/board/detail")
	public ModelAndView detail(@RequestParam("bNum") int bNum)throws Exception{
		ModelAndView mv = new ModelAndView("board/boardDetail.html");
		
		BoardDto board = ProTodoService.selectBoardDetail(bNum);
		mv.addObject("board", board);
				
		return mv;
	}
	@RequestMapping("/main/board/update")
	   public String updateBoard(BoardDto board) throws Exception {
	      ProTodoService.updateBoard(board);
	      return "redirect:/main/board";
	   }
	   
	   @RequestMapping("/main/board/delete")
	   public String deleteBoard(@RequestParam("bNum") int bNum) throws Exception {
		   ProTodoService.deleteBoard(bNum);
	      return "redirect:/main/board";
	   }
	
	@RequestMapping("/main/board/write")
	 public String write()throws Exception{
		return "board/boardWrite.html";
	}
	@PostMapping("/main/board/write")
	public String addboard(BoardDto board, HttpSession session) throws Exception{
		String memberId = (String) session.getAttribute("memberId");
		board.setMemberId(memberId);
		ProTodoService.addBoard(board);
		return "redirect:/main/board";
	}
	
	//====================================================================team
	@RequestMapping("/main/team")
	 public String team(HttpSession session, Model model)throws Exception{
		// 현재 로그인된 사용자 ID 가져오기 (세션 사용)
        String memberId = (String) session.getAttribute("memberId");

        // 팀 정보 가져오기
        List<TeamDto> teamList = ProTodoService.getTeamInfo(memberId);

        // 팀이 존재하는지 여부 확인
        boolean hasTeam = !teamList.isEmpty();
        boolean isLeader = hasTeam && teamList.stream()
                .anyMatch(team -> team.getMemberId().equals(memberId) && team.getIsLeader() == 1);

        // 모델에 데이터 추가
        model.addAttribute("memberId", memberId);
        model.addAttribute("hasTeam", hasTeam);
        model.addAttribute("isLeader", isLeader);
        model.addAttribute("teamList", teamList);

        return "board/team.html"; // Thymeleaf 템플릿 (team.html)
	}
	@RequestMapping("/main/team/addteam")
	public String teamadd(HttpSession session, TeamDto team)throws Exception{
		String memberId = (String) session.getAttribute("memberId");
		team.setMemberId(memberId);
		ProTodoService.addTeam(team);
		return "redirect:/main/team";
	}
	@RequestMapping("/main/team/deletebyid")
	public String teamdeletebyid(HttpSession session)throws Exception{
		String memberId = (String) session.getAttribute("memberId");
		ProTodoService.deleteTeambyId(memberId);
		return "redirect:/main/team";
	}
	@RequestMapping("/main/team/deletebytname")
	public String teamdeletebytname(@RequestParam("tName") String tName)throws Exception{
		ProTodoService.deleteTeambyTeamName(tName);
		return "redirect:/main/team";
	}
	@RequestMapping("/main/team/teamset")
	public String teamset(Model model, HttpSession session)throws Exception{
		String memberId = (String) session.getAttribute("memberId");
		List<TeamDto> teamList = ProTodoService.selectTeamSet(memberId);
		// Thymeleaf에서 사용할 데이터 추가
		model.addAttribute("memberId", memberId);
		model.addAttribute("teamList", teamList); 
		return "board/teamSet.html";
	}
	@RequestMapping("/main/team/teamset/addTeamMember")
	public String addteammember(TeamDto team)throws Exception{
		ProTodoService.addTeamMember(team);
		return "redirect:/main/team/teamset";
	}
	
	//====================================================================mypage
	@RequestMapping("/main/mypage")
	 public ModelAndView mypage(HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView("board/mypage.html");
		// 세션에서 사용자 ID 가져오기
	    String memberId = (String) session.getAttribute("memberId");
	    
	    // userId를 기준으로 데이터 조회
	    MemberDto member = ProTodoService.selectMemberById(memberId);
	    mv.addObject("member", member);
		
		return mv;
	}
	@RequestMapping("/main/mypage/updatemember")
	 public String updatemember(@ModelAttribute MemberDto member,HttpSession session)throws Exception{
		//세션에서 id가져오기
		String memberId = (String) session.getAttribute("memberId");
		
		member.setMemberId(memberId);
		ProTodoService.updateMember(member);
		
		return "redirect:/main/mypage";
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
		ProTodoService.addMember(member);
		return "redirect:/main/login";
	}
}
