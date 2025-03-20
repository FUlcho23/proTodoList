package org.big.dto;

import lombok.Data;

@Data
public class TeamDto {

	private int tId; //-- 팀 번호 (자동 증가)
	private String tName; //-- 팀명
	private String memberId; //-- 팀원 아이디 (member과 연결)
	private String tRole; //-- 직책
	
	private int hasTeam;  // 팀이 있는지 여부(쿼리추가)
    private int isLeader; // 팀장 여부(쿼리추가)
}
