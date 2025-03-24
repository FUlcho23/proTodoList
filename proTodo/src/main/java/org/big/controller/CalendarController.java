package org.big.controller;

import java.time.Instant;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.big.dto.TodoDto;
import org.big.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalendarController {
	@Autowired
	private CalendarService calendarService;
	
	//달력 일정 조회
	@RequestMapping("/selectTodo")
    public List<TodoDto> calendarList() throws Exception{
        List<TodoDto> todo = calendarService.selectTodo();
        return todo;
    }
	
	//달력 일정 추가
	@PostMapping("/addTodo")
    public TodoDto calendarSave(@RequestBody Map<String, Object> map) throws Exception {

		TodoDto todo = new TodoDto();
		todo.setTdTodo((String) map.get("tdTodo"));

		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

		// UTC 시간을 Instant로 변환
		Instant startInstant = Instant.parse((String) map.get("tdStart"));
		Instant endInstant = map.get("tdEnd") != null ? Instant.parse((String) map.get("tdEnd")) : null;

		// Instant를 한국 시간대로 변환 (ZoneId.of("Asia/Seoul"))
		LocalDateTime startLocal = LocalDateTime.ofInstant(startInstant, ZoneId.of("Asia/Seoul"));
		LocalDateTime endLocal = endInstant != null ? LocalDateTime.ofInstant(endInstant, ZoneId.of("Asia/Seoul")) : null;

		// 최종적으로 한국 시간 형식으로 저장
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		todo.setTdStart(startLocal);
		todo.setTdEnd(endLocal);
		todo.setTdAllday(map.get("tdAllday") != null ? ((Number) map.get("tdAllday")).intValue() : 0);

        // 저장한 일정의 key 값을 포함한 데이터를 다시 반환
        calendarService.addTodo(todo);

        return todo;
    }
	
	//캘린터 일정 삭제
	@DeleteMapping("/deleteTodo")
    public String calendarDelete(@RequestParam String no) throws Exception{
        try{
            calendarService.deleteTodo(no);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
	
	//캘린더 일정 수정
	@PutMapping("/updateTodo/{tdId}")
    public String eventUpdate(@PathVariable int tdId, @RequestBody Map<String, Object> map){

		TodoDto todo = new TodoDto();
		todo.setTdId(tdId);
		todo.setTdTodo((String) map.get("TdTodo"));
		
		 DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

		// UTC 시간을 Instant로 변환
		Instant startInstant = Instant.parse((String) map.get("start"));
		Instant endInstant = map.get("end") != null ? Instant.parse((String) map.get("end")) : null;

		// Instant를 한국 시간대로 변환
		LocalDateTime startLocal = LocalDateTime.ofInstant(startInstant, ZoneId.of("Asia/Seoul"));
		LocalDateTime endLocal = endInstant != null ? LocalDateTime.ofInstant(endInstant, ZoneId.of("Asia/Seoul")) : null;

		// 변환된 LocalDateTime을 TodoDto에 저장
		todo.setTdStart(startLocal);
		todo.setTdEnd(endLocal);
		
        todo.setTdAllday(map.get("allDay")!= null ? ((Number) map.get("allDay")).intValue() : 0);

        try {
            calendarService.updateTodo(todo);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
	
}
