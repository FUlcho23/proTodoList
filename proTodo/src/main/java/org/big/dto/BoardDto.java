package org.big.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardDto {
	private int bNum; //자동증가(게시글번호)
	private String memberId; //작성자 아이디
	private String bSubject; //게시글 제목
	private String bContent; //게시글 내용
	private LocalDateTime bRegistDay; //작성일시
	private int bHit; //조회수(자동증가-인데 이제 내가 증가하라 해줘야 하는)
	

}
