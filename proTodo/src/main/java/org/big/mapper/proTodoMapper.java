package org.big.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.big.dto.BoardDto;
import org.big.dto.MemberDto;
import org.big.dto.TeamDto;

@Mapper
public interface proTodoMapper {

   List<String> getFilePathsByBoardIdx(int boardIdx);
   List<BoardDto> selectBoardList() throws Exception;
   MemberDto loginFindMember(@Param("memberId") String memberId);
   void addMember(MemberDto member) throws Exception;
   MemberDto selectMemberbyId(@Param("memberId") String memberId);
   public void updateMember(MemberDto member) throws Exception;
   void addBoard(BoardDto board) throws Exception;
   BoardDto selectBoardDetail(@Param("bNum")int bNum);
   void updateBoard(BoardDto board) throws Exception;
   void deleteBoard(int bNum) throws Exception;
   List<TeamDto> selectTeam(String memberId);
   void addTeam(TeamDto team) throws Exception;
   void deleteTeambyId(String memberId) throws Exception;
   void deleteTeambyTeamName(String tName) throws Exception;
   void addTeamMember(TeamDto team) throws Exception;
   List<TeamDto> selectTeamSet(String memberId);
   void updateHitCount(int bNum) throws Exception;
}