package org.big.service;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.big.commom.FileUtils;
import org.big.dto.BoardDto;
import org.big.dto.BoardFileDto;
import org.big.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
/* @Transactional */
@Transactional(rollbackFor = Exception.class)
public class BoardServiceImpl implements BoardService{
   
   @Autowired
   private BoardMapper boardMapper;

   @Autowired
   private FileUtils fileUtils;
   
   @Override
   public List<BoardDto> selectBoardList() throws Exception {
      // TODO Auto-generated method stub
      return boardMapper.selectBoardList();
   }

   @Override
   public void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
      // TODO Auto-generated method stub
      boardMapper.insertBoard(board);
      List<BoardFileDto>list=fileUtils.parseFileInfo(board.getBoardIdx(), multipartHttpServletRequest);
      if(CollectionUtils.isEmpty(list)==false) {
    	  boardMapper.insertBoardFileList(list);
      }
   }
   
   @Override
   public void updateHitCount(int boardIdx) throws Exception {
      // TODO Auto-generated method stub
      boardMapper.updateHitCount(boardIdx);
   }
   
   @Override
   public BoardDto selectBoardDetail(int boardIdx) throws Exception {
      // TODO Auto-generated method stub
      BoardDto board = boardMapper.selectBoardDetail(boardIdx);
      
      List<BoardFileDto> fileList=boardMapper.selectBoardFileList(boardIdx);
      board.setFileList(fileList);
 
      return board;
   }
   
   @Override
   public void updateBoard(BoardDto board) throws Exception {
      // TODO Auto-generated method stub
      boardMapper.updateBoard(board);
   }
   
   @Override
   public void deleteBoard(int boardIdx) throws Exception {
      // TODO Auto-generated method stub
      
      List<String> filePaths = boardMapper.getFilePathsByBoardIdx(boardIdx);
      
      for (String filePath : filePaths) {
           File file = new File(filePath);
           if (file.exists() && file.isFile()) {
               boolean isDeleted = file.delete();
               if (isDeleted) {
                   System.out.println("파일 삭제 성공: " + filePath);
               } else {
                   System.out.println("파일 삭제 실패: " + filePath);
               }
           }
       }
      
      boardMapper.deleteBoard(boardIdx);
      boardMapper.deleteBoardFile(boardIdx);
   }
   @Override
   public BoardFileDto selectBoardFileInformation(int idx, int boardIdx) throws Exception{
	   return boardMapper.selectBoardFileInformation(idx, boardIdx);
   }
   

}







