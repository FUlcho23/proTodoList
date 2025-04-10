package org.big.service;

import java.util.List;

import org.big.mapper.*;
import org.apache.ibatis.annotations.Param;
import org.big.dto.BoardDto;
import org.big.dto.MemberDto;
import org.big.dto.TeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProTodoServiceImpl implements ProTodoService{
	
	@Autowired
	private proTodoMapper proTodoMapper;

	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		// TODO Auto-generated method stub
		return proTodoMapper.selectBoardList();
	}
	@Override
	public void addMember(MemberDto member) throws Exception {
	    proTodoMapper.addMember(member);
	}
	@Override
	public MemberDto selectMemberById(String memberId) {
        return proTodoMapper.selectMemberbyId(memberId);
    }
	@Override
	public void updateMember(MemberDto member) throws Exception {
		proTodoMapper.updateMember(member);
	}
	@Override
	public void addBoard(BoardDto board) throws Exception {
		proTodoMapper.addBoard(board);
	}
	@Override
	public BoardDto selectBoardDetail(int bNum) {
		return proTodoMapper.selectBoardDetail(bNum);
	}
	@Override
	public void updateBoard(BoardDto board) throws Exception {
		proTodoMapper.updateBoard(board);
		
	}
	@Override
	public void deleteBoard(int bNum) throws Exception {
		proTodoMapper.deleteBoard(bNum);
		
	}
	@Override
	public List<TeamDto> getTeamInfo(String memberId) {
		return proTodoMapper.selectTeam(memberId);
	}
	@Override
	public void addTeam(TeamDto team) throws Exception {
		proTodoMapper.addTeam(team);
	}
	@Override
	public void deleteTeambyId(String memberId) throws Exception {
		proTodoMapper.deleteTeambyId(memberId);
		
	}
	@Override
	public void deleteTeambyTeamName(String tName) throws Exception {
		proTodoMapper.deleteTeambyTeamName(tName);
	}
	@Override
	public void addTeamMember(TeamDto team) throws Exception {
		proTodoMapper.addTeamMember(team);
		
	}
	@Override
	public List<TeamDto> selectTeamSet(String memberId) {
		return proTodoMapper.selectTeamSet(memberId);
	}
	@Override
	public void updateHitCount(int bNum) throws Exception {
		proTodoMapper.updateHitCount(bNum);		
	}
	
}
