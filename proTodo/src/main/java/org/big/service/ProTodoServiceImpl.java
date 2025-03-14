package org.big.service;

import java.util.List;

import org.big.mapper.*;
import org.big.dto.BoardDto;
import org.big.dto.MemberDto;
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
	public void memberAdd(MemberDto member) throws Exception {
	    proTodoMapper.memberAdd(member);
	}

}
