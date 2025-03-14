package org.big.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MemberDto {
	
	private String memberId;
	private String mPassword;
	private String mName;
	private String mPhone;
	private String mEmail;
	private LocalDateTime mRegistDay; //작성일시
	
}
