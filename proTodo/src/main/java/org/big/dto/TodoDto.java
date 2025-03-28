package org.big.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TodoDto {
	private int tdId; //할일 고유 ID(자동 증가) == calendarNo
	private String memberId;//생성한 사람 nn
	private String tdWorkM; //일이 배분된 팀원-본인도 가능
	private String tName; //팀 이름
	private String tdTodo; //일정 nn
	private String tdStatus; //상태(td_status IN ('Y'완료, 'N'아직, 'P'진행중)), 디폴트N
	private String tdFile; //첨부파일 (선택 사항)
	private int tdHidden;//DEFAULT 0 CHECK (td_hidden IN (0, 1)) 공개여부(기본 비공개0)
	
	private LocalDateTime tdEnd;//마감일
	private LocalDateTime tdStart;//시작일
	
	private int tdAllday;//하루종일인 일정인가

}

/*
 * private Long calendarNo; 
 * private String title; 
 * private String start1; 
 * private String end;
 * private boolean allDay;
 * 음 대충 캘린더넘버, 제목, 시작일, 마감일, 하루종일(T,F)
 
 */
