package org.big.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.big.dto.BoardDto;
import org.big.dto.MemberDto;

@Mapper
public interface proTodoMapper {


   List<String> getFilePathsByBoardIdx(int boardIdx);
   List<BoardDto> selectBoardList() throws Exception;
   MemberDto loginFindMember(@Param("memberId") String memberId);
   void memberAdd(MemberDto member) throws Exception;
   

}