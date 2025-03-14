package org.big.service;

import java.util.List;

import org.big.dto.BoardDto;
import org.big.dto.MemberDto;

public interface ProTodoService {
	List<BoardDto> selectBoardList() throws Exception;
	void memberAdd(MemberDto member) throws Exception;
}
