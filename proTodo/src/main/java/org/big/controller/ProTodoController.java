package org.big.controller;

import java.util.List;

import org.big.dto.BoardDto;
import org.big.dto.MemberDto;
import org.big.dto.TeamDto;
import org.big.dto.TodoDto;
import org.big.mapper.CalenderMapper;
import org.big.mapper.proTodoMapper;
import org.big.service.CalendarService;
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
	private CalendarService calendarService; 
	
	@RequestMapping("/main/todo") public String todo(HttpSession session, Model model)throws Exception{
		String tdWorkM = (String) session.getAttribute("memberId");
        List<TodoDto> todoList = calendarService.selectTodo(tdWorkM);
        List<TodoDto> sharedList = calendarService.selectTodoByTeam(tdWorkM);
        
        model.addAttribute("sharedList", sharedList);
        model.addAttribute("todoList", todoList);  // Thymeleaf에서 사용
		return "board/todo.html"; 
	}
	
	@RequestMapping("/home")
	 public String main(HttpSession session)throws Exception{
		 
	    return "board/main.html";
	}
	@RequestMapping("/session-expired")
	 public String main()throws Exception{
		 
	    return "board/sessionError.html";
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
		
		ProTodoService.updateHitCount(bNum);
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
		// 현재 로그인된 사용자 ID
        String memberId = (String) session.getAttribute("memberId");

        // 팀 정보 가져오기
        List<TeamDto> teamList = ProTodoService.getTeamInfo(memberId);

        // 팀 존재여부 확인
        boolean hasTeam = !teamList.isEmpty();
        boolean isLeader = hasTeam && teamList.stream()
                .anyMatch(team -> team.getMemberId().equals(memberId) && team.getIsLeader() == 1);
        // 모델에 데이터 추가
        model.addAttribute("memberId", memberId);
        model.addAttribute("hasTeam", hasTeam);
        model.addAttribute("isLeader", isLeader);
        model.addAttribute("teamList", teamList);

        return "board/team.html";
	}
	@RequestMapping("/main/team/addteam")
	public String teamadd(HttpSession session, TeamDto team, RedirectAttributes redirectAttrs)throws Exception{
		String memberId = (String) session.getAttribute("memberId");
		team.setMemberId(memberId);
		
		String tName = team.getTName();
		if(ProTodoService.selectExistsBytName(tName)) {
			redirectAttrs.addFlashAttribute("msg", "이미 존재하는 팀 이름 입니다.");
		}
		else {
			ProTodoService.addTeam(team);
		}
		
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
		model.addAttribute("memberId", memberId);
		model.addAttribute("teamList", teamList); 
		return "board/teamSet.html";
	}
	@RequestMapping("/main/team/teamset/addTeamMember")
	public String addteammember(TeamDto team, RedirectAttributes redirectAttrs)throws Exception{
		String memberId = team.getMemberId();
		if(ProTodoService.selectExistsBymId(memberId)) {
			if (!ProTodoService.selectTeamExistsBymId(memberId)) {
		        ProTodoService.addTeamMember(team);
		    } else {
		        redirectAttrs.addFlashAttribute("msg", "이미 팀에 소속된 사용자입니다.");
		    }
		}
		else {
			 redirectAttrs.addFlashAttribute("msg", "존재하지 않는 사용자입니다.");
		}
		return "redirect:/main/team/teamset";
	}
	@RequestMapping("/main/team/teamset/delTeamMember")
	public String delteammember(TeamDto team)throws Exception{
		ProTodoService.deleteTeamMember(team.getMemberId());
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
		String memberId = (String) session.getAttribute("memberId");
		
		member.setMemberId(memberId);
		ProTodoService.updateMember(member);
		
		return "redirect:/main/mypage";
	}
	@RequestMapping("/main/mypage/deletemember")
	 public String deletemember(HttpSession session)throws Exception{
		String memberId = (String) session.getAttribute("memberId");
		
		ProTodoService.deleteMember(memberId);
		session.invalidate();
		return "redirect:/home";
	}
	@RequestMapping("/main/login")
	 public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        
        return "board/login.html"; 
    }
	@PostMapping("/main/login")
    public String loginProcess(@RequestParam String id, @RequestParam String pwd,HttpSession session, RedirectAttributes redirectAttrs) {
		
		// ✅ DB에서 사용자 조회
	    MemberDto memberId = ProTodoService.loginFindMember(id);

	    if (memberId == null || !memberId.getMPassword().equals(pwd)) {
	    	redirectAttrs.addFlashAttribute("msg", "아이디 또는 비밀번호를 확인해주세요.");
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
	@GetMapping("/login/memberadd")
	 public String memberaddhome(Model model, @ModelAttribute("member") MemberDto member)throws Exception{
		if (!model.containsAttribute("member")) {
			model.addAttribute("member", new MemberDto());
		}
		return "board/memberadd.html";
	}
	@PostMapping("/login/memberadd")
	public String memberadd(MemberDto member, RedirectAttributes rA) throws Exception{
		String mPhone = member.getMPhone();
		String mEmail = member.getMEmail();
		MemberDto checklist = ProTodoService.selectExistsByPE(mPhone, mEmail);
		if(!ProTodoService.selectExistsBymId(member.getMemberId())) {
			if(checklist.getPhoneExists()==0){
				if(checklist.getEmailExists()==0) {
					ProTodoService.addMember(member);
					return "redirect:/main/login";
				}
				else {
					rA.addFlashAttribute("msg", "이미 가입된 이메일입니다.");
					rA.addFlashAttribute("member", member); 
					return "redirect:/login/memberadd";
				}
			}
			else {
				rA.addFlashAttribute("msg", "이미 가입된 전화번호입니다.");
				rA.addFlashAttribute("member", member); 
				return "redirect:/login/memberadd";
			}
		}
		else {
			rA.addFlashAttribute("msg", "이미 존재하는 아이디입니다.");
			rA.addFlashAttribute("member", member); 
			return "redirect:/login/memberadd";
		}
	}
}
