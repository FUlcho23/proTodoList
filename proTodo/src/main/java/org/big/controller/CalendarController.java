package org.big.controller;

import java.time.Instant;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.big.dto.TodoDto;
import org.big.mapper.CalenderMapper;
import org.big.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class CalendarController {
	@Autowired
	private CalendarService calendarService; 
	
	//달력 일정 조회 
	@RequestMapping("/todoData")
	@ResponseBody
	public List<TodoDto> getTodoData(HttpSession session) throws Exception {
		String tdWorkM = (String) session.getAttribute("memberId");
		
	    return calendarService.selectTodo(tdWorkM);
	}
	
	//달력 일정 추가
	@PostMapping("/addTodo")
    public TodoDto calendarSave(@RequestBody Map<String, Object> map, HttpSession session) throws Exception {
		String memberId = (String) session.getAttribute("memberId");

        TodoDto todo = new TodoDto();
        todo.setMemberId(memberId);
        todo.setTdWorkM(memberId);
        todo.setTdTodo((String) map.get("tdTodo"));

        // UTC 시간을 LocalDateTime으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
     // 날짜 변환 (ISO 형식을 오라클 형식으로 변환)
        // tdStart 변환
        if (map.get("tdStart") != null) {
            String startDate = map.get("tdStart").toString();
            
            
            if (startDate.length() < 19) {
                // 시간이 없으면 "00:00:00" 추가
            	startDate = startDate + " 00:00:00".substring(startDate.length() - 10);
                todo.setTdStart(LocalDateTime.parse(startDate, formatter));
            }
            else {
            	// ✅ OffsetDateTime을 사용하여 변환
	            LocalDateTime localStartDate = OffsetDateTime.parse(startDate).toLocalDateTime();
	            todo.setTdStart(localStartDate);
            }
        }

        // tdEnd 변환
        if (map.get("tdEnd") != null) {
            String endDate = map.get("tdEnd").toString();
            
            if (endDate.length() < 19) {
                // 시간이 없으면 "00:00:00" 추가
                endDate = endDate + " 00:00:00".substring(endDate.length() - 10);
                todo.setTdEnd(LocalDateTime.parse(endDate, formatter));
            }
            else {
            	LocalDateTime localEndDate = OffsetDateTime.parse(endDate).toLocalDateTime();
                todo.setTdEnd(localEndDate);
            }
        }
        
        System.out.println("tdAllday: " + map.get("tdAllday"));
        
        todo.setTdAllday(map.get("tdAllday")!= null ? ((Number) map.get("tdAllday")).intValue() : 0);
        
        String tName = calendarService.getTeamName(memberId);
        todo.setTName(tName != null ? tName : ""); // NULL 방지
        
        // 저장한 일정의 key 값을 포함한 데이터를 다시 반환
        calendarService.addTodo(todo);
        
        return todo;
    }
	
	//캘린터 일정 삭제
	@DeleteMapping("/deleteTodo")
    public String calendarDelete(@RequestParam String tdId) throws Exception{
        try{
            calendarService.deleteTodo(tdId);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
	//일정 수정
	@PutMapping("/updateTodo/{tdId}")
    public String eventUpdate(@PathVariable int tdId, @RequestBody Map<String, Object> map){

        TodoDto todo = new TodoDto();
        todo.setTdId(tdId);
        todo.setTdTodo((String) map.get("tdTodo"));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        // 날짜 변환 (ISO 형식을 오라클 형식으로 변환)
        if (map.get("tdStart") != null) {
            String startDate = map.get("tdStart").toString().replace("T", " ").substring(0, 19);  
            todo.setTdStart(LocalDateTime.parse(startDate, formatter));
        }
        if (map.get("tdEnd") != null) {
            String endDate = map.get("tdEnd").toString().replace("T", " ").substring(0, 19);
            todo.setTdEnd(LocalDateTime.parse(endDate, formatter));
        }
       todo.setTdAllday(map.get("tdAllday")!= null ? ((Number) map.get("tdAllday")).intValue() : 0);

        try {
            calendarService.updateTodo(todo);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
	
	@PostMapping("/updateStatus")
	@ResponseBody
	public Map<String, Object> updateStatus(@RequestBody Map<String, Object> requestData) {
	    try {
	        List<Integer> ids = ((List<?>) requestData.get("ids")).stream()
	            .map(id -> Integer.parseInt(id.toString()))
	            .collect(Collectors.toList());

	        String status = requestData.get("status").toString();

	        calendarService.updateStatus(ids, status);

	        return Collections.singletonMap("success", true);
	    } catch (Exception e) {
	        e.printStackTrace(); // 로그 확인용
	        return Collections.singletonMap("success", false);
	    }
	}

	@PostMapping("/updateHidden")
	@ResponseBody
	public Map<String, Object> updateHidden(@RequestBody Map<String, Object> payload) {
	    List<Integer> ids = ((List<?>) payload.get("ids")).stream()
	        .map(id -> Integer.parseInt(id.toString()))
	        .collect(Collectors.toList());

	    int hidden = (int) payload.get("hidden");

	    calendarService.updateHidden(ids, hidden);

	    Map<String, Object> response = new HashMap<>();
	    response.put("success", true);
	    return response;
	}
	
}
