package org.big.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.big.dto.BoardDto;
import org.big.dto.MemberDto;
import org.big.dto.TeamDto;

public interface ProTodoService {
	List<BoardDto> selectBoardList() throws Exception;
	void updateHitCount(int bNum) throws Exception;
	void addBoard(BoardDto board) throws Exception;
	BoardDto selectBoardDetail(int bNum);
	void updateBoard(BoardDto board) throws Exception;
	void deleteBoard(int bNum) throws Exception;
	
	MemberDto loginFindMember(@Param("memberId") String memberId);
	void addMember(MemberDto member) throws Exception;
	MemberDto selectMemberById(String memberId);
	public void updateMember(MemberDto member) throws Exception;
	void deleteMember(String memberId);
	
	List<TeamDto> getTeamInfo(String memberId);
	void addTeam(TeamDto team) throws Exception;
	void deleteTeambyId(String memberId) throws Exception;
	void deleteTeambyTeamName(String tName) throws Exception;
	void addTeamMember(TeamDto team) throws Exception;
	List<TeamDto> selectTeamSet(String memberId);
	void deleteTeamMember(String memberId);
	
	boolean selectExistsBymId(String memberId);
	boolean selectTeamExistsBymId(String memberId);
	boolean selectExistsBytName(String nName);
	MemberDto selectExistsByPE(
			   @Param("mPhone")String mPhone, @Param("mEmail")String mEmail);
}
