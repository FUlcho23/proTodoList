package org.big.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.big.dto.BoardDto;
import org.big.dto.BoardFileDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Mapper
public interface BoardMapper {

   List<BoardDto> selectBoardList() throws Exception;
   
   List<String> getFilePathsByBoardIdx(int boardIdx);

   
   void insertBoard(BoardDto board) throws Exception;
   void updateHitCount(int boardIdx) throws Exception;
   void updateBoard(BoardDto board) throws Exception;
   void deleteBoard(int boardIdx) throws Exception;
   BoardDto selectBoardDetail(int boardIdx) throws Exception;
   void insertBoardFileList(List<BoardFileDto> list) throws Exception;
   void deleteBoardFile(int boardIdx);
   List<BoardFileDto> selectBoardFileList(int boardIdx) throws Exception;
   BoardFileDto selectBoardFileInformation(@Param("idx") int idx, @Param("boardIdx") int boardIdx);
}