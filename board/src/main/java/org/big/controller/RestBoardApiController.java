package org.big.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.big.dto.BoardDto;
import org.big.dto.BoardFileDto;
import org.big.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class RestBoardApiController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/api/board", method = RequestMethod.GET)
	public List<BoardDto> openBoardList() throws Exception {
		return boardService.selectBoardList();
	}

	/*
	 * @RequestMapping(value = "/api/board/write", method = RequestMethod.GET)
	 * public void openBoardWrite() throws Exception { return
	 * "/board/restBoardWrite"; }
	 */

	@RequestMapping(value = "/api/board/write", method = RequestMethod.POST)
	public void insertBoard(@RequestBody BoardDto board) throws Exception {
		boardService.insertBoard(board, null);
	}

	@RequestMapping(value = "/api/board/{boardIdx}", method = RequestMethod.GET)
	public BoardDto openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
		return boardService.selectBoardDetail(boardIdx);
	}

	@RequestMapping(value = "/api/board/{boardIdx}", method = RequestMethod.PUT)
	public String updateBoard(@RequestBody BoardDto board) throws Exception {
		boardService.updateBoard(board);
		return "redirect:/board";
	}

	@RequestMapping(value = "/api/board/{boardIdx}", method = RequestMethod.DELETE)
	public String deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception {
		boardService.deleteBoard(boardIdx);
		return "redirect:/board";
	}

	/*
	 * @RequestMapping(value = "/board/file", method = RequestMethod.GET) public
	 * void downloadBoardFile(@RequestParam("idx") int
	 * idx, @RequestParam("boardIdx") int boardIdx, HttpServletResponse response)
	 * throws Exception { BoardFileDto boardFile =
	 * boardService.selectBoardFileInformation(idx, boardIdx); if
	 * (ObjectUtils.isEmpty(boardFile) == false) { String fileName =
	 * boardFile.getOriginalFileName();
	 * 
	 * byte[] files = FileUtils.readFileToByteArray(new
	 * File(boardFile.getStoredFilePath()));
	 * 
	 * response.setContentType("application/octet-stream");
	 * response.setContentLength(files.length);
	 * response.setHeader("Content-Disposition", "attachment; fileName=\"" +
	 * URLEncoder.encode(fileName, "UTF-8") + "\";");
	 * response.setHeader("Content-Transfer-Encoding", "binary");
	 * 
	 * response.getOutputStream().write(files); response.getOutputStream().flush();
	 * response.getOutputStream().close(); } }
	 */

}
